package com.george.fluffy.foxy.server.voltage.repository;

import com.george.fluffy.foxy.server.voltage.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}