package uz.pdp.codingbatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Languages;
import uz.pdp.codingbatapi.entity.Users;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.LanguageDto;
import uz.pdp.codingbatapi.payload.UsersDto;
import uz.pdp.codingbatapi.repository.LanguageRepository;
import uz.pdp.codingbatapi.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    UsersRepository usersRepository;

    public  List<Languages> getLanguages(){
        List<Languages> languages = languageRepository.findAll();
        return languages;
    }

    public Languages getLanguagesById(Integer id){
        Optional<Languages> optionalLanguages = languageRepository.findById(id);
        return optionalLanguages.orElse(null);
    }

    public ApiResponse addLanguages( LanguageDto languageDto){
        Languages languages = new Languages();
        languages.setName(languageDto.getName());
        languages.setUsersId(usersRepository.getById(languageDto.getUserId()));
        languageRepository.save(languages);
        return new ApiResponse("Language saqlandi!",true);
    }


    public ApiResponse editLanguage(Integer id, LanguageDto languageDto){
        boolean existsByNameAndIdNot = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
        if (existsByNameAndIdNot){
            return new ApiResponse("Bunday idlik language mavjud emas!",false);
        }
        Optional<Languages> optionalLanguages = languageRepository.findById(id);
        if (optionalLanguages.isEmpty()){
            return new ApiResponse("Bunday idlik mavjud",false);
        }
        Languages languages = optionalLanguages.get();
        languages.setName(languageDto.getName());
        languages.setUsersId(usersRepository.getById(languageDto.getUserId()));
        languageRepository.save(languages);
        return new ApiResponse("Language tahrirlandi!",true);

    }

    public ApiResponse deleteLanguage(Integer id){
        languageRepository.deleteById(id);
        return new ApiResponse("Language ochirildi!",true);
    }
}
