package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.Languages;
import uz.pdp.codingbatapi.entity.Users;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.LanguageDto;
import uz.pdp.codingbatapi.payload.UsersDto;
import uz.pdp.codingbatapi.repository.UsersRepository;
import uz.pdp.codingbatapi.service.LanguageService;

import java.util.List;

@RestController
public class LanguageController {
    @Autowired
    LanguageService languageService;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/api/language")
    public ResponseEntity<List<Languages>> getLanguages(){
        List<Languages> languages = languageService.getLanguages();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/api/language/{id}")
    public HttpEntity<Languages> getLanguageById(@PathVariable Integer id){
        Languages languagesById = languageService.getLanguagesById(id);
        return ResponseEntity.ok(languagesById);
    }

    @PostMapping("/api/language")
    public HttpEntity<ApiResponse> addLanguage(@RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.addLanguages(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/language/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable Integer id, @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/language/{id}")
    public HttpEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

}
