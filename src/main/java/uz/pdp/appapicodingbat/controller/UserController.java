package uz.pdp.appapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodingbat.entity.User;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.UserDto;
import uz.pdp.appapicodingbat.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/user")
    public HttpEntity<ApiResponse> addUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<ApiResponse> editUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id){
        ApiResponse apiResponse = userService.editUser(userDto, id);
        return  ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
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
