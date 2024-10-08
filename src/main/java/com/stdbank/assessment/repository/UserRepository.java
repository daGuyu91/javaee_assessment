package com.stdbank.assessment.repository;

import com.stdbank.assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  {
     void saveUser(User user);
     User updateUser(User user);
     User findUserById(String id);
     List<User> findAllUsers();
     void deleteUser(String id);
}
