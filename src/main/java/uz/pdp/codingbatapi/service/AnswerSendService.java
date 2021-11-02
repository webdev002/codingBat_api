package uz.pdp.codingbatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.AnswerSend;
import uz.pdp.codingbatapi.payload.AnswerSendDto;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.repository.AnswerSendRepository;
import uz.pdp.codingbatapi.repository.TaskRepository;
import uz.pdp.codingbatapi.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerSendService {

    @Autowired
    AnswerSendRepository answerSendRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UsersRepository usersRepository;

    public List<AnswerSend> getAnswerSend(){
        List<AnswerSend> answerSends = answerSendRepository.findAll();
        return answerSends;
    }


    public AnswerSend getAnswerSendById(Integer id){
        Optional<AnswerSend> optionalAnswerSend = answerSendRepository.findById(id);
        if (optionalAnswerSend.isEmpty()){
            return optionalAnswerSend.get();
        }
        return null;
    }

    public ApiResponse addAnswerSend(AnswerSendDto answerSendDto){
        boolean existsByText = answerSendRepository.existsByText(answerSendDto.getText());
        if (existsByText){
            return new ApiResponse("Bunday javob oldin yuborilgan!",false);
        }
        AnswerSend answerSend = new AnswerSend();
        answerSend.setText(answerSendDto.getText());
        answerSend.setUsersId(usersRepository.getById(answerSendDto.getUsersId()));
        answerSend.setTaskId(taskRepository.getById(answerSendDto.getTaskId()));
        answerSendRepository.save(answerSend);
        return new ApiResponse("Answer send yuborildi",true);
    }

    public ApiResponse editAnswerSend(Integer id, AnswerSendDto answerSendDto){
        boolean existsByTextAndIdNot = answerSendRepository.existsByTextAndIdNot(answerSendDto.getText(), id);
        if (existsByTextAndIdNot){
            return new ApiResponse("Bunday idlik answer send mavjud emas!",false);
        }
        Optional<AnswerSend> optionalAnswerSend = answerSendRepository.findById(id);
        if (!optionalAnswerSend.isPresent()){
            return new ApiResponse("Bunday idlik answer send mavjud!",false);
        }
        AnswerSend answerSend = optionalAnswerSend.get();
        answerSend.setText(answerSendDto.getText());
        answerSend.setUsersId(usersRepository.getById(answerSendDto.getUsersId()));
        answerSend.setTaskId(taskRepository.getById(answerSendDto.getTaskId()));
        answerSendRepository.save(answerSend);
        return new ApiResponse("Answer send Tahrirlandi",true);
    }

    public ApiResponse deleteAnswerSend(Integer id){
        answerSendRepository.deleteById(id);
        return new ApiResponse("Answer send ochirildi!",true);
    }
}
