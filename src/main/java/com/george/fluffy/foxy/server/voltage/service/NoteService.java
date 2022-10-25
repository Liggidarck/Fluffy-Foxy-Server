package com.george.fluffy.foxy.server.voltage.service;

import com.george.fluffy.foxy.server.voltage.model.Note;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    List<Note> getAllNotes();

    ResponseEntity<String> createNote(Note note);

    ResponseEntity<String> updateNote(long id, Note note);

    ResponseEntity<String> deleteNote(long id);

    Note getNoteById(long id);

}
