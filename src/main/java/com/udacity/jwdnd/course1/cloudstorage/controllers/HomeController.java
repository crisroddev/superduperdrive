package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
  @Autowired
  private FileService fileService;

  @Autowired
  private UserService userService;

  @Autowired
  private NoteService noteService;

  @Autowired
  private CredentialService credentialService;

  @RequestMapping()
  public String homeView(Authentication authentication, Model model){
    String username = authentication.getName();
    User user = userService.getUser(username);
    model.addAttribute("files", fileService.getAllFiles(user.getUserid()));
    model.addAttribute("notes", noteService.getAllNotes(user.getUserid()));
    model.addAttribute("credentials", credentialService.getAllCredentials(user.getUserid()));
    return "home";
  }
}
