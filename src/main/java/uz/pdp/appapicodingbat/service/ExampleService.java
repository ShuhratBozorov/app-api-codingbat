package uz.pdp.appapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapicodingbat.entity.Example;
import uz.pdp.appapicodingbat.entity.Task;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.ExampleDto;
import uz.pdp.appapicodingbat.repository.ExampleRepository;
import uz.pdp.appapicodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<Example> getExamples() {
        return exampleRepository.findAll();
    }

    public Example getExampleById(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse addExample(ExampleDto exampleDto) {

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTask_id());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday task mavjud emas!", false);
        Example example = new Example();
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example added", true);
    }

    public ApiResponse editExample(ExampleDto exampleDto, Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("Bunday example mavjud emas",false);

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTask_id());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday task mavjud emas!", false);

        Task task = optionalTask.get();

        Example example = optionalExample.get();
        example.setText(exampleDto.getText());
        example.setTask(task);
        exampleRepository.save(example);
        return new ApiResponse("Example edited", true);
    }

    public ApiResponse deleteExample(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("Example deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
