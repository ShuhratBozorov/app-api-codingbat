package uz.pdp.appapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapicodingbat.entity.Language;
import uz.pdp.appapicodingbat.entity.Task;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.TaskDto;
import uz.pdp.appapicodingbat.repository.LanguageRepository;
import uz.pdp.appapicodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LanguageRepository languageRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public ApiResponse addTask(TaskDto taskDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday Language mavjud emas!", false);

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setSolution(taskDto.getSolution());
        task.setMethod(taskDto.getMethod());
        task.setText(taskDto.getText());
        task.setLanguage(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Task added", true);
    }

    public ApiResponse editTask(TaskDto taskDto, Integer id) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday task mavjud emas",false);

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday Language mavjud emas!", false);
        Language language = optionalLanguage.get();

        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setSolution(taskDto.getSolution());
        task.setMethod(taskDto.getMethod());
        task.setText(taskDto.getText());
        task.setLanguage(language);
        taskRepository.save(task);
        return new ApiResponse("Task edited", true);
    }

    public ApiResponse deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Task deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
