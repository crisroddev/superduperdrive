package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
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
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) throws IOException {
        String fileError = null;
        User curr = userService.getUser(authentication.getName());
        if(fileService.isFileAvailable(multipartFile, curr.getUserid())){
            try {
                fileService.uploadFile(multipartFile, curr.getUserid());
                model.addAttribute("fileUploadSuccess", "File Uploaded");
            } catch (Exception e) {
                fileError = e.toString();
                model.addAttribute("fileError", fileError);
            }
        } else {
            model.addAttribute("fileError", "Cant Upload File name already in use, please select a new file name");
        }
        model.addAttribute("files", fileService.getFiles(curr.getUserid()));
        model.addAttribute("notes", noteService.getNotes(curr));
        model.addAttribute("credentials", credentialService.getAllCredentials(curr));
        return "home";
    }

    @RequestMapping("/file/{fileId}")
    public ResponseEntity downloadFile(@PathVariable("fileId") Integer fileId, Authentication authentication, Model model) throws IOException {
        File file = fileService.getFileById(fileId);
        String contentType = file.getContentType();
        String fileName = file.getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file.getFileData());
    }

    @RequestMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId, Authentication authentication, Model model) throws IOException {
        String fileError = null;
        try {
             fileService.deleteFile(fileId);
             model.addAttribute("fileDeleteSuccess", "File deleted");
        }catch (Exception e) {
            fileError = e.toString();
            model.addAttribute("fileError", fileError);
        }
        User curr = userService.getUser(authentication.getName());
        model.addAttribute("files", fileService.getFiles(curr.getUserid()));
        model.addAttribute("notes", noteService.getNotes(curr));
        model.addAttribute("credentials", credentialService.getAllCredentials(curr));
        return "home";
    }
}
