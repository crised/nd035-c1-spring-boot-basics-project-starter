package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
    public String homeView(Model model) {
        return "home";
    }

    @ModelAttribute("files")
    public File[] allfiles() {
        /**
         * Fake file list creator, for testing purposes.
         */
        File file1 = new File();
        File file2 = new File();
        file1.setFileId(1);
        file1.setFilename("file_1");
        file2.setFileId(2);
        file2.setFilename("file_2");
        return new File[]{file1, file2};
    }

}
