package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public boolean handleFileUpload(MultipartFile fileUpload, String username) {
        Integer userId = userService.getUserIdByUserName(username);
        if (userId < 0) return false;
        byte[] fileArray;
        try {
            fileArray = fileUpload.getBytes();
            System.out.println(fileUpload.getOriginalFilename());
        } catch (IOException e) {
            System.out.println("Couldn't upload file");
            return false;
        }
        File file = new File(fileUpload.getName(), fileUpload.getContentType(),
                String.valueOf(fileArray.length), userId, fileArray);
        fileMapper
        System.out.println("Upload file!: " + fileUpload.getSize());
        return true;
    }

    public List<File> listFiles() {
        return fileMapper.getAllFiles();
    }
}
