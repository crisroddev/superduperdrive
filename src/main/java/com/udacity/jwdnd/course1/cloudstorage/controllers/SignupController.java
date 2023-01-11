package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
  private UserService userService;

  public SignupController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping()
  public String signupView() { return "signup"; }
  @PostMapping()
  public String signupUser(@ModelAttribute User user, Model model) {
    String error = null;
    if(!userService.isUsernameAvailable(user.getUsername())) {
      error = "Username already exists please choose a new one";
    }

    if (error == null) {
      int rows = userService.createUser(user);
      if (rows < 0) {
        error = "There was an error please try again";
      }
    }

    if (error == null) {
      model.addAttribute("signUpSuccess", true);
    } else {
      model.addAttribute("signupError", error);
    }
    return "signup";
  }
}