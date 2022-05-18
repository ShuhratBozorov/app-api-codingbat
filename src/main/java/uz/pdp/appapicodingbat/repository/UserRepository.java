package uz.pdp.appapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodingbat.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
}
