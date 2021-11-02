package uz.pdp.codingbatapi.service;

import org.hibernate.hql.internal.classic.AbstractParameterInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.entity.Users;
import uz.pdp.codingbatapi.payload.ApiResponse;
import uz.pdp.codingbatapi.payload.UsersDto;
import uz.pdp.codingbatapi.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> getUsers(){
        List<Users> users = usersRepository.findAll();
        return users;
    }

    public Users getUsersById(Integer id){
        Optional<Users> optionalUsers = usersRepository.findById(id);
//        if (optionalUsers.isPresent()){
//            return optionalUsers.get();
//        }
//        return null;
        return optionalUsers.orElse(null);
    }

    public ApiResponse addUsers(UsersDto usersDto){
        boolean existsByPasswordAndEmail = usersRepository.existsByPasswordAndEmail(usersDto.getPassword(), usersDto.getEmail());
        if (existsByPasswordAndEmail){
            return new ApiResponse("Bunday password va emailga ega user mavjud!",false);
        }
        Users users = new Users();
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        usersRepository.save(users);
        return new ApiResponse("Users saqlandi!",true);
    }

    public ApiResponse editUsers(Integer id , UsersDto usersDto){
        boolean existsByPasswordAndIdNot = usersRepository.existsByPasswordAndIdNot(usersDto.getPassword(), id);
        if (existsByPasswordAndIdNot){
            return new ApiResponse("Bunday idlik users mavjud emas!",false);
        }
        Optional<Users> usersRepositoryById = usersRepository.findById(id);
        if (!usersRepositoryById.isPresent()){
            return new ApiResponse("Bunday idlik users mavjud!",false);
        }
        Users users = usersRepositoryById.get();
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        usersRepository.save(users);
        return new ApiResponse("Users tahrirlandi!",true);
    }

    public ApiResponse deleteUsers(Integer id){
        usersRepository.deleteById(id);
        return new ApiResponse("Users ochirildi!",true);
    }
}
