package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public void uploadFile(MultipartFile multipartFile, Integer userId) throws Exception {
        File newFile = new File(
          multipartFile.getOriginalFilename(),
          multipartFile.getContentType(),
          multipartFile.getSize(),
          multipartFile.getBytes(),
          userId
        );
        try {
            fileMapper.save(newFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isFileAvailable(MultipartFile multipartFile, Integer userId) {
        Boolean isFileAvailable = true;
        List<File> files = fileMapper.findFilesByUserId(userId);
        for(int i = 0; i < files.size(); i++){
            File curr = files.get(i);
            if(curr.getFileName().equals(multipartFile.getOriginalFilename())){
                isFileAvailable = false;
                break;
            }
        }
        return isFileAvailable;
    }

    public List<File> getFiles(Integer userId) {
        return fileMapper.findFilesByUserId(userId);
    }

    public File getFileById(Integer fileId) {
        return fileMapper.findById(fileId);
    }

    public void deleteFile(Integer fileId) throws IOException {
        try {
            fileMapper.deleteById(fileId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
