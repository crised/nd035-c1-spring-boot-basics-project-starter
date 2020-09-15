package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String getNote(@ModelAttribute("noteForm") Note noteForm, Authentication authentication, Model model) {
        List<Note> notes = noteService.getNotes(authentication.getName());
        System.out.println("notes: " + notes.size());
        return "home";
    }

    @PostMapping
    public String receiveNote(@ModelAttribute("noteForm") Note noteForm, Authentication authentication, Model model) {
        System.out.println("on post receiveNote");
        if (noteForm.getNoteid() == null)
            noteService.addNote(noteForm, authentication.getName());
        else noteService.updateNote(noteForm);
        return "redirect:/home";
    }


}
