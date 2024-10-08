package com.stdbank.assessment.service;

import com.stdbank.assessment.model.User;
import com.stdbank.assessment.repository.UserRepository;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.saveUser(user);
    }
    public void deleteUser(User user){
        userRepository.deleteUser(String.valueOf(user.getId()));
    }
    public void updateUser(User user){
        userRepository.updateUser(user);
    }
    public User findUserById(int id){
        return userRepository.findUserById(String.valueOf(id));
    }
    public List<User> getAllUsers(){
        return userRepository.findAllUsers();
    }

}
