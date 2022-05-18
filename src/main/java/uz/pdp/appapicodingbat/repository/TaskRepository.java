package uz.pdp.appapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodingbat.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
