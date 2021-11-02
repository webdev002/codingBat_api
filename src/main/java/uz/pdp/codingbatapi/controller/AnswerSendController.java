package uz.pdp.codingbatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.entity.AnswerSend;
import uz.pdp.codingbatapi.payload.AnswerSendDto;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.service.AnswerSendService;

import java.util.List;

@RestController
public class AnswerSendController {
    @Autowired
    AnswerSendService answerSendService;

    @GetMapping("/api/answer-send")
    public ResponseEntity<List<AnswerSend>> getAnswerSend(){
        List<AnswerSend> answerSend = answerSendService.getAnswerSend();
        return ResponseEntity.ok(answerSend);
    }

    @GetMapping("/api/answer-send/{id}")
    public HttpEntity<AnswerSend> getAnswerSendById(@PathVariable Integer id){
        AnswerSend answerSendById = answerSendService.getAnswerSendById(id);
        return ResponseEntity.ok(answerSendById);
    }

    @PostMapping("/api/answer-send")
    public ResponseEntity<ApiResponse> addAnswerSend(@RequestBody AnswerSendDto answerSendDto){
        ApiResponse apiResponse = answerSendService.addAnswerSend(answerSendDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/answer-send/{id}")
    public ResponseEntity<ApiResponse> editAnswerSend(@PathVariable Integer id, @RequestBody AnswerSendDto answerSendDto){
        ApiResponse apiResponse = answerSendService.editAnswerSend(id, answerSendDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/answer-send/{id}")
    public ResponseEntity<ApiResponse> deleteAnswerSend(@PathVariable Integer id){
        ApiResponse apiResponse = answerSendService.deleteAnswerSend(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

}
