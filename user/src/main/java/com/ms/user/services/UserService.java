package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

   final UserRepository userRepository;
   final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer){
       this.userRepository = userRepository;
       this.userProducer = userProducer;
   }

   public List<UserModel> getAllUsers(){
       return this.userRepository.findAll();
   }

   @Transactional
   public UserModel saveUser(UserModel userModel){
       userModel = this.userRepository.save(userModel);
       userProducer.pusblishMessage(userModel);
       return userModel;
   }


}
