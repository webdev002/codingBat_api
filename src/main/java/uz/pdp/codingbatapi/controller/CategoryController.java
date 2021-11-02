package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.CategoryDto;
import uz.pdp.codingbatapi.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/category")
    public HttpEntity<List<Category>> getCategory(){
        List<Category> category = categoryService.getCategory();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
        Category categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryById);
    }

    @PostMapping("/api/category")
    public HttpEntity<ApiResponse> addCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

}
