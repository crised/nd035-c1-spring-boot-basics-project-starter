package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homeView(@ModelAttribute("credentialForm") Credential credentialForm, @ModelAttribute("noteForm") Note noteForm, Model model) {
        return "home";
    }

    @PostMapping()
    public String controlFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload,
                                    Authentication authentication,
                                    Model model) {
        model.addAttribute("error", null); //reset the error
        if (!fileService.handleFileUpload(fileUpload, authentication.getName()))
            model.addAttribute("error", "Couldn't upload the file, maybe it's duplicated?");
        model.addAttribute("files", allFilesFromUser(authentication));
        return "home";
    }


    @ModelAttribute("files")
    public List<File> allFilesFromUser(Authentication authentication) {
        User user = userService.getUser(authentication.getName()); // TODO: Delete UserService
        return fileService.listFilesByUserId(Integer.valueOf(user.getUserid()));
    }

    @ModelAttribute("notes")
    public List<Note> allNotesFromUser(Authentication authentication) {
        return noteService.getNotes(authentication.getName());
    }

    @ModelAttribute("credentials")
    public List<Credential> allCredentialsFromUser(Authentication authentication) {
        return credentialService.getCredentials(authentication.getName());

    }


}
