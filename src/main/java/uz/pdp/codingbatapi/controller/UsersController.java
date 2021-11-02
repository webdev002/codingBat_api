package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Users;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.UsersDto;
import uz.pdp.codingbatapi.service.UsersService;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/api/users")
    public HttpEntity<List<Users>> getUsers(){
        List<Users> users = usersService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable Integer id){
        Users usersById = usersService.getUsersById(id);
        return ResponseEntity.ok(usersById);
    }
    @PostMapping("/api/users")
    public HttpEntity<ApiResponse> addUsers(@RequestBody UsersDto usersDto){
        ApiResponse apiResponse = usersService.addUsers(usersDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<ApiResponse> editUsers(@PathVariable Integer id,  @RequestBody UsersDto usersDto){
        ApiResponse apiResponse = usersService.editUsers(id, usersDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<ApiResponse> deleteUsers(@PathVariable Integer id){
        ApiResponse apiResponse = usersService.deleteUsers(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
}
