package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.TaskDto;
import uz.pdp.codingbatapi.service.TaskService;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/api/task")
    public ResponseEntity<List<Task>> getTask(){
        List<Task> task = taskService.getTask();
        return ResponseEntity.ok(task);
    }
    @GetMapping("/api/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        Task taskById = taskService.getTaskById(id);
        return ResponseEntity.ok(taskById);
    }
    @PostMapping("/api/task")
    public ResponseEntity<ApiResponse> addTask(@RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/task/{id}")
    public HttpEntity<ApiResponse> editTask(@PathVariable Integer id , @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.editTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/task/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
}
