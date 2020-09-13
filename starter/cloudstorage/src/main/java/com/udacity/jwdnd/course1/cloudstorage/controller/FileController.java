package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file-upload")
public class FileController {

    @PostMapping()
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload,
                                    Model model) {
        try {
            InputStream fis = fileUpload.getInputStream();
            byte[] file = fis.readAllBytes();

        } catch (IOException e) {
            System.out.println("Couldn't upload file");
        }
//        System.out.println("Upload file!: " + fileForm.getFileName());
        System.out.println("Upload file!: " + fileUpload.getSize());
        return "home";
    }


}
