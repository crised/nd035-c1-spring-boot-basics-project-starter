package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
    public String getId(Integer param) {
        System.out.println("here");
        System.out.println(param);
        return "home";
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(@RequestParam("fileId") String fileId) throws IOException {
        // Accepting url of type: http://localhost:8080/file/download?fileId=001
        System.out.println(fileId);
        File file = fileService.getFileByFileId(Integer.valueOf(fileId));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename());
        ByteArrayResource resource = new ByteArrayResource(file.getFiledata());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.getFiledata().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam("fileId") String fileId) {
        //http://localhost:8080/file/delete?fileId=1
        fileService.deleteFileByFileId(Integer.valueOf(fileId));

        return "home";
    }

    @PostMapping()
    public String controlFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload,
                                    Authentication authentication,
                                    Model model) {
        fileService.handleFileUpload(fileUpload, authentication.getName());
        return "home";
    }
}
