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

    private boolean success;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public boolean addNote(Note note, String username) {
        success = false;
        User user = userService.getUser(username);
        if (user != null) {
            noteMapper.insert(new Note(note.getNotetitle(), note.getNotedescription(), user.getUserid()));
            success = true;
            return true;
        }
        return false;
    }

    public boolean updateNote(Note note) {
        success = false;
        noteMapper.update(note);
        success = true;
        return true;
    }

    public List<Note> getNotes(String username) {
        User user = userService.getUser(username);
        if (user == null) return null;
        return noteMapper.getAllNotesFromUser(user.getUserid());
    }

    public boolean deleteNote(Integer noteid) {
        success = false;
        if (noteMapper.deleteNote(noteid) == 1) {
            success = true;
            return true;
        }
        return false;
    }

    public boolean isSuccess() {
        return success;
    }
}
