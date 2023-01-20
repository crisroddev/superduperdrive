package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CredentialController {
  private UserService userService;
  private CredentialService credentialService;
  private NoteService noteService;
  private FileService fileService;
  public CredentialController(UserService userService, CredentialService credentialService, NoteService noteService, FileService fileService) {
    this.userService = userService;
    this.credentialService = credentialService;
    this.noteService = noteService;
    this.fileService = fileService;
  }

  @PostMapping("/credential-upload")
  public String uploadCredential(Authentication authentication, @ModelAttribute Credential credential, Model model) throws IOException {
    String credentialError = null;
    String credentialUploadError = null;
    User curr = userService.getUser(authentication.getName());

    if(credential.getCredentialId() == null) {
      try {
        credential.setUserid(curr.getUserid());
        credentialService.createCredential(credential);
        model.addAttribute("credentialUploadSuccess", "Credential Uploaded");
      } catch (Exception e) {
        credentialUploadError = e.toString();
        model.addAttribute("credentialError", credentialUploadError);
      }
    } else {
      try {
        Credential currCredential = credentialService.getCredential(credential.getCredentialId());
        credentialService.updateCredential(currCredential);
      } catch (Exception e) {
        credentialError = e.toString();
        model.addAttribute("credentialError", credentialError);
      }
    }
    model.addAttribute("files", fileService.getFiles(curr));
    model.addAttribute("notes", noteService.getNotes(curr));
    model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    return "home";
  }

  @GetMapping("/decrypt")
  public void decryptPassword(HttpServletResponse response, Authentication authentication, @ModelAttribute Credential credential) throws IOException {
    User curr = userService.getUser(authentication.getName());
    credential = credentialService.getCredential(credential.getCredentialId());
    if(credential != null && credential.getUserid().intValue() == curr.getUserid().intValue()) {
      String decrypted = credentialService.decryptPassword(credential);
      response.getWriter().println(decrypted);
    }
  }

  @RequestMapping("/credential-delete/{credentialId}")
  public String deleteCredential(Authentication authentication, @ModelAttribute Credential credential, Model model) throws IOException{
    String deleteError = null;
    User curr = userService.getUser(authentication.getName());
    try {
      credentialService.deleteCredential(credential);
      model.addAttribute("deleteCredentialSuccess", "Credential Deleted");
    } catch (Exception e) {
      deleteError = e.toString();
      model.addAttribute("deleteError", deleteError);
    }
    model.addAttribute("files", fileService.getFiles(curr));
    model.addAttribute("notes", noteService.getNotes(curr));
    model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    return "home";
  }
}
