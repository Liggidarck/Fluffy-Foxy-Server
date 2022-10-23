package com.george.fluffy.foxy.server.controller;

import com.george.fluffy.foxy.server.model.Note;
import com.george.fluffy.foxy.server.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping
    public String getWelcome() {
        return "Welcome";
    }


    @GetMapping(value = "/get/notes")
    public List<Note> getNotes() {
        return (List<Note>) noteRepository.findAll();
    }

    @PostMapping(value = "/create/note")
    public String saveNote(@RequestBody Note note) {
        noteRepository.save(note);
        return "200";
    }

}
