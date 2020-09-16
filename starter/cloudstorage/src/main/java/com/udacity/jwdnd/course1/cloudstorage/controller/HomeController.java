package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homeView(@RequestParam(value = "tabid", required = false) String tabid,
                           @ModelAttribute("credentialForm") Credential credentialForm,
                           @ModelAttribute("noteForm") Note noteForm, Model model) {
        return "home";
    }

    @ModelAttribute("files")
    public List<File> allFilesFromUser(Authentication authentication) {
        return fileService.listFilesByUserName(authentication.getName());
    }

    @ModelAttribute("notes")
    public List<Note> allNotesFromUser(Authentication authentication) {
        return noteService.getNotes(authentication.getName());
    }

    @ModelAttribute("credentials")
    public List<Credential> allCredentialsFromUser(Authentication authentication) {
        return credentialService.getCredentials(authentication.getName());
    }

    @ModelAttribute("error")
    public String getFileErrorMessage(){
        return fileService.getErrorMessage();
    }

}
