package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.InvalidNameException;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class FileService {

  Logger logger = LoggerFactory.getLogger(FileService.class);
  private FileMapper fileMapper;

  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  public boolean createFile(File file) throws InvalidNameException {
    if(fileMapper.countFilesByFilename(file) > 0) {
      throw new InvalidNameException(("The file name: " + file.getName() + " already exists"));
    }
    return fileMapper.insert(file) == 1;
  }

  public File uploadFile(MultipartFile multipartFile, User user) {
    File file = null;
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    try {
      file = new File(
              null,
              multipartFile.getOriginalFilename(),
              multipartFile.getContentType(),
              String.valueOf(multipartFile.getSize()),
              user.getUserid(),
              multipartFile.getBytes()
      );
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return file;
  }

  public boolean updateFile(File file) {
    return fileMapper.update(file) == 1;
  }

  public File getFile(Integer fileId) {
    return fileMapper.getFile(fileId);
  }

  public ArrayList<File> getFiles(User user) {
    return fileMapper.getFiles(user);
  }

  public boolean deleteFile(File file) {
    return fileMapper.deleteFile(file.getFileId()) == 1;
  }

  public int deleteAllFiles() {
    return fileMapper.deleteAll();
  }
}
