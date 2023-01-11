package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
  private UserMapper userMapper;
  private HashService hashService;

  public UserService(UserMapper userMapper, HashService hashService) {
    this.userMapper = userMapper;
    this.hashService = hashService;
  }

  public boolean isUsernameAvailable(String username){
    return userMapper.getUser(username) == null;
  }

  public int createUser(User user){
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String hashedPassword = hashService.getHashed(user.getPassword(), encodedSalt);
    return userMapper.insert(
            new User(user.getUsername(), user.getFirstname(), user.getLastname(), user.getPassword(), hashedPassword)
    );
  }

  public User getUser(String username){
    return userMapper.getUser(username);
  }
}
