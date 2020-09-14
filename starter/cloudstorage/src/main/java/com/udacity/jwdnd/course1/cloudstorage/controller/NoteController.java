package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    @GetMapping
    public String getNote(@ModelAttribute("noteForm") Note noteForm, Model model) {
        return "home";
    }

    @PostMapping
//    public String receiveNote(@RequestAttribute("note") Note note, Model model) {
    public String receiveNote(@ModelAttribute("noteForm") Note noteForm, Model model) {
        System.out.println("on post receiveNote");
        System.out.println(noteForm.getNotetitle());
        System.out.println(noteForm.getNotedescription());
        return "home";
    }


}
