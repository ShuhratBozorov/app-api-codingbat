package uz.pdp.appapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapicodingbat.entity.Category;
import uz.pdp.appapicodingbat.entity.User;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.CategoryDto;
import uz.pdp.appapicodingbat.payload.UserDto;
import uz.pdp.appapicodingbat.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByNameAndDescription(categoryDto.getName(),categoryDto.getDescription());
        if (exists) {
            return new ApiResponse("Bunday category mavjud", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return new ApiResponse("Category saved", true);
    }

    public ApiResponse editCategory(CategoryDto categoryDto, Integer id) {
        boolean exists = categoryRepository.existsByNameAndDescription(categoryDto.getName(),categoryDto.getDescription());
        if (exists) {
            return new ApiResponse("Bunday category mavjud", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday category mavjud emas", false);

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return new ApiResponse("Category edited", true);
    }

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
