package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping()
    public String upload(@RequestParam("fileUpload") MultipartFile fileUpload,
                                    Authentication authentication,
                                    Model model) {
        fileService.setErrorMessage(null);
        if (!fileService.handleFileUpload(fileUpload, authentication.getName()))
            fileService.setErrorMessage("Couldn't upload the file, maybe it's duplicated?");
        return "redirect:/home";
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(@RequestParam("fileId") String fileId) throws IOException {
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
        fileService.deleteFileByFileId(Integer.valueOf(fileId));
        return "redirect:/home";
    }


}
