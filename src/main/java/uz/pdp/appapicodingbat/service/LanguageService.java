package uz.pdp.appapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapicodingbat.entity.Language;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.LanguageDto;
import uz.pdp.appapicodingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public List<Language> getLanguages() {
        List<Language> languages = languageRepository.findAll();
        return languages;
    }

    public Language getLanguageById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    public ApiResponse addLanguage(LanguageDto languageDto) {

        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language saved", true);
    }

    public ApiResponse editLanguage(LanguageDto languageDto, Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday language mavjud emas", false);

        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language edited", true);
    }

    public ApiResponse deleteLanguage(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Language deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
