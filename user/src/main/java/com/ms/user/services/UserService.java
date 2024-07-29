package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

   final UserRepository userRepository;

   public UserService(UserRepository userRepository){
       this.userRepository = userRepository;
   }

   public List<UserModel> getAllUsers(){
       return this.userRepository.findAll();
   }

   @Transactional
   public UserModel saveUser(UserModel userModel){
       return this.userRepository.save(userModel);
   }


}
