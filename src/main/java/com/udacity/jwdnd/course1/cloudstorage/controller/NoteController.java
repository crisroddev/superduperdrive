package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {
  private UserService userService;
  private NoteService noteService;
  public NoteController(UserService userService, NoteService noteService) {
    this.userService = userService;
    this.noteService = noteService;

  }

  @PostMapping("/note-upload")
  public String createNote(Authentication authentication, @ModelAttribute Note note, Model model) {
  return null;
  }
}
