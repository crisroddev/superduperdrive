package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
//import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileController {
  private NoteService noteService;
  private UserService userService;
  private FileService fileService;
  private CredentialService credentialService;

  public FileController(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService) {
    this.noteService = noteService;
    this.userService = userService;
    this.fileService = fileService;
    this.credentialService = credentialService;
  }

  @PostMapping("/upload-file")
  public String uploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
    User curr = userService.getUser(authentication.getName());
    String fileUploadError = null;
    if (curr != null) {
      try {
        fileService.uploadFile(multipartFile, curr);
        model.addAttribute("fileUploadSuccess", "File was uploaded");

      } catch (Exception e){
        fileUploadError = e.toString();
        model.addAttribute("uploadError", fileUploadError);
      }
    } else {
      model.addAttribute("fileError", "File upload error please try again");
    }
    model.addAttribute("files", fileService.getFiles(curr));
    model.addAttribute("notes", noteService.getNotes(curr));
    model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    return "home";
  }

//  @GetMapping("/download")
//  public void downloadFile(HttpServletResponse response, @ModelAttribute File file) throws IOException {
//    file = fileService.getFile(file.getFileId());
//    if(file != null) {
//      response.setContentType(file.getContentType());
//      response.setContentLength(Integer.valueOf(file.getSize()));
//      String key = "Content-Disposition";
//      String value = "attachment; filename=" + StringEscapeUtils.escapeHtml4(file.getName());
//      response.setHeader(key, value);
//      response
//              .getOutputStream()
//              .write(file.getData());
//    }
//  }

  @PostMapping("/delete-file")
  public String deleteFile(Authentication authentication, @ModelAttribute File file, Model model) throws IOException{
    User curr = userService.getUser(authentication.getName());
    String fileDeleteError = null;
    try {
      fileService.deleteFile(file);
      model.addAttribute("deleteSuccess", "File deleted");

    } catch (Exception e){
      fileDeleteError = e.toString();
      model.addAttribute("fileDeleteError", fileDeleteError);
    }
    model.addAttribute("files", fileService.getFiles(curr));
    model.addAttribute("notes", noteService.getNotes(curr));
    model.addAttribute("credentials", credentialService.getAllCredentials(curr));
    return "home";
  }
}
