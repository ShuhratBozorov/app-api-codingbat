package uz.pdp.appapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodingbat.entity.Example;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.ExampleDto;
import uz.pdp.appapicodingbat.service.ExampleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    @GetMapping("/api/example")
    public ResponseEntity<List<Example>> getExamples(){
        List<Example> examples = exampleService.getExamples();
        return ResponseEntity.ok(examples);
    }

    @GetMapping("/api/example/{id}")
    public ResponseEntity<Example> getExampleById(@PathVariable Integer id){
        Example example = exampleService.getExampleById(id);
        return ResponseEntity.ok(example);
    }

    @PostMapping("/api/example")
    public HttpEntity<ApiResponse> addExample(@Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/example/{id}")
    public ResponseEntity<ApiResponse> editExample(@Valid @RequestBody ExampleDto exampleDto, @PathVariable Integer id){
        ApiResponse apiResponse = exampleService.editExample(exampleDto, id);
        return  ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/example/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
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
