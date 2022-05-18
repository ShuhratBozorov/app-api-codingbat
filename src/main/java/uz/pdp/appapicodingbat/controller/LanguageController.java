package uz.pdp.appapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodingbat.entity.Language;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.LanguageDto;
import uz.pdp.appapicodingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping("/api/language")
    public ResponseEntity<List<Language>> getCompanies(){
        List<Language> companies = languageService.getLanguages();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/api/language/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Integer id){
        Language language = languageService.getLanguageById(id);
        return ResponseEntity.ok(language);
    }

    @PostMapping("/api/language")
    public HttpEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/language/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@Valid @RequestBody LanguageDto languageDto, @PathVariable Integer id){
        ApiResponse apiResponse = languageService.editLanguage(languageDto, id);
        return  ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/language/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
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
