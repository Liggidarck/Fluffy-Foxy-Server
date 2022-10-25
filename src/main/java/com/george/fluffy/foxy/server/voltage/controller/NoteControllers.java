package com.george.fluffy.foxy.server.voltage.controller;

import com.george.fluffy.foxy.server.voltage.model.Note;
import com.george.fluffy.foxy.server.voltage.repository.NoteRepository;
import com.george.fluffy.foxy.server.voltage.service.NoteService;
import com.george.fluffy.foxy.server.utils.ThereIsNoSuchObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class NoteControllers implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @GetMapping(value = "/get/note/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public Note getNoteById(@PathVariable long id) {
        Note note = noteRepository.findById(id).orElse(null);

        if(note == null) {
            throw new ThereIsNoSuchObjectException();
        }

        return note;
    }

    @Override
    @GetMapping(value = "/get/note")
    @PreAuthorize("hasRole('DEVELOPER')")
    public List<Note> getAllNotes() {
        return (List<Note>) noteRepository.findAll();
    }

    @Override
    @PostMapping(value = "/save/note")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<String> createNote(@RequestBody Note note) {
        noteRepository.save(note);
        return ResponseEntity.status(HttpStatus.OK).body("Note successfully created");
    }

    @Override
    @PutMapping(value = "/edit/note/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<String > updateNote(@PathVariable long id, @RequestBody Note note) {
        Note updateNote = noteRepository.findById(id).orElse(null);

        if(updateNote == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");

        updateNote.setTitle(note.getTitle());
        updateNote.setDescription(note.getDescription());
        noteRepository.save(updateNote);

        return ResponseEntity.status(HttpStatus.OK).body("Note successfully edited");
    }

    @Override
    @DeleteMapping(value = "/delete/note/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<String> deleteNote(@PathVariable long id) {
        Note deleteNote = noteRepository.findById(id).orElse(null);
        if(deleteNote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }

        noteRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Note successfully deleted");
    }


}
