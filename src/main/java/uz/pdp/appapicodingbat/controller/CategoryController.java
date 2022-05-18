package uz.pdp.appapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodingbat.entity.Category;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.CategoryDto;
import uz.pdp.appapicodingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/category")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/api/category")
    public HttpEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        ApiResponse apiResponse = categoryService.editCategory(categoryDto, id);
        return  ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
