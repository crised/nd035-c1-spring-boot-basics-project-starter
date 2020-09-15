package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public boolean addNote(String title, String description, String username) {
        User user = userService.getUser(username);
        if (user != null) {
            Note note = new Note(title, description, user.getUserid());
            noteMapper.insert(note);
            return true;
        }
        return false;
    }

    public List<Note> getNotes(String username) {
        User user = userService.getUser(username);
        if (user == null) return null;
        return noteMapper.getAllNotesFromUser(user.getUserid());
    }

}
