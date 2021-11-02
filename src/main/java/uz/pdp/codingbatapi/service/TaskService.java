package uz.pdp.codingbatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.TaskDto;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getTask(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public Task getTaskById(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public ApiResponse addTask(TaskDto taskDto){
        boolean existsByName = taskRepository.existsByName(taskDto.getName());
        if (existsByName){
            return new ApiResponse("Bunday task mavjud!",false);
        }
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setExample(taskDto.getExample());
        task.setCategoryId(categoryRepository.getById(taskDto.getCategoryId()));
        taskRepository.save(task);
        return new ApiResponse("Task saqlandi!",true);
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto){
        boolean existsByNameAndIdNot = taskRepository.existsByNameAndIdNot(taskDto.getName(), id);
        if (existsByNameAndIdNot){
            return new ApiResponse("Bunday task mavjud emas!",false);
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new ApiResponse("Bunday idlik task mavjud emas!",false);
        }
        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setExample(taskDto.getExample());
        task.setCategoryId(categoryRepository.getById(taskDto.getCategoryId()));
        taskRepository.save(task);
        return new ApiResponse("Task tahrirlandi", true);
    }

    public ApiResponse deleteTask(Integer id){
        taskRepository.deleteById(id);
        return new ApiResponse("Task ochirildi!",true);
    }
}
