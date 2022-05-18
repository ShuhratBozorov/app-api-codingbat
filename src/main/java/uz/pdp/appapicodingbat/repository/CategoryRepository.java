package uz.pdp.appapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodingbat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByNameAndDescription(String name, String description);
}
