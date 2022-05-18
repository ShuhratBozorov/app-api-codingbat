package uz.pdp.appapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapicodingbat.entity.Task;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.TaskDto;
import uz.pdp.appapicodingbat.service.TaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/api/task")
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/api/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/api/task")
    public HttpEntity<ApiResponse> addTask(@Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/task/{id}")
    public ResponseEntity<ApiResponse> editTask(@Valid @RequestBody TaskDto taskDto, @PathVariable Integer id){
        ApiResponse apiResponse = taskService.editTask(taskDto, id);
        return  ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
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
