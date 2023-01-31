package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class NoteController {
  private UserService userService;
  private NoteService noteService;
  private FileService fileService;
  private CredentialService credentialService;

  private Logger log = LoggerFactory.getLogger(NoteController.class);

  public NoteController(UserService userService, NoteService noteService, FileService fileService, CredentialService credentialService) {
    this.userService = userService;
    this.noteService = noteService;
    this.fileService = fileService;
    this.credentialService = credentialService;
  }

  @PostMapping("/note-upload")
  public String editNote(Authentication authentication, @ModelAttribute Note note, Model model) throws IOException {
    String noteError = null;
    String noteUploadError = null;
    User curr = userService.getUser(authentication.getName());
    if(note.getNoteId() == null) {
      try {
        note.setUserid(curr.getUserid());
        noteService.createNote(note);
        model.addAttribute("noteUploadSuccess", "Note created");

      } catch (Exception e){
        noteError = e.toString();
        model.addAttribute("noteError", noteError);
        System.out.println(noteError);
      }
    } else {
      try {
        noteService.updateNote(note);
        model.addAttribute("updateSuccess", "Note Updated");

      }catch (Exception e){
        noteUploadError = e.toString();
        model.addAttribute("uploadError", noteUploadError);
        System.out.println(noteUploadError);
      }
    }
    model.addAttribute("files", fileService.getFiles(curr));
    model.addAttribute("notes", noteService.getNotes(curr));
    model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    return "home";
  }

  @RequestMapping("/note-delete/{id}")
  public String deleteNote(Authentication authentication, @ModelAttribute Note note, Model model) throws IOException {
    String deleteError = null;
    User curr = userService.getUser(authentication.getName());
    note = noteService.getNote(note.getNoteId());
    if(note != null){
      try {
        noteService.deleteNote(note);
        model.addAttribute("deleteSuccess", "Note Deleted");
      } catch (Exception e) {
        deleteError = e.toString();
        model.addAttribute("deleteError", deleteError);
      }
      model.addAttribute("files", fileService.getFiles(curr));
      model.addAttribute("notes", noteService.getNotes(curr));
      model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    }
   
    return "home";
  }
}
