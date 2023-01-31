package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
  @Autowired
  private UserService userService;
  @Autowired
  private NoteService noteService;
  @Autowired
  private FileService fileService;
  @Autowired
  private CredentialService credentialService;

  public HomeController(UserService userService, NoteService noteService, CredentialService credentialService, FileService fileService) {
    this.userService = userService;
    this.noteService = noteService;
    this.credentialService = credentialService;
    this.fileService = fileService;
  }

  public Logger log = LoggerFactory.getLogger(HomeController.class);

  @GetMapping
  public String displayHome(Authentication authentication, Model model) {
    User curr = userService.getUser(authentication.getName());
    model.addAttribute(curr);
    model.addAttribute("files", fileService.getFiles(curr));
    model.addAttribute("notes", noteService.getNotes(curr));
    model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    return "home";
  }
}
