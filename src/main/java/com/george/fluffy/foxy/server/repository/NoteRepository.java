package com.george.fluffy.foxy.server.repository;

import com.george.fluffy.foxy.server.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}