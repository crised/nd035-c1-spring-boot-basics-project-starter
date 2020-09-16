package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserService userService;
    private String errorMessage;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public boolean handleFileUpload(MultipartFile fileUpload, String username) {
        String filename = fileUpload.getOriginalFilename();
        if(filename.isEmpty()) return false;
        if (fileMapper.getFileByName(filename) != null) return false;
        Integer userId = userService.getUserIdByUserName(username);
        if (userId < 0) return false;
        byte[] fileArray;
        try {
            fileArray = fileUpload.getBytes();
        } catch (IOException e) {
            System.out.println("Couldn't upload file");
            return false;
        }
        File file = new File(filename, fileUpload.getContentType(),
                String.valueOf(fileArray.length), userId, fileArray);
        fileMapper.addFile(file);
        return true;
    }

    public List<File> listFilesByUserName(String username) {
        User user = userService.getUser(username);
        return fileMapper.getFilesFromUser(user.getUserid());
    }

    public File getFileByFileId(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public boolean deleteFileByFileId(Integer fileId) {
        if (fileMapper.deleteFile(fileId) == 1) return true;
        return false;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
