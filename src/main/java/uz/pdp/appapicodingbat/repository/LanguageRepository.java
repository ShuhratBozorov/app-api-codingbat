package uz.pdp.appapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodingbat.entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
