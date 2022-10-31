package com.george.fluffy.foxy.server.palladium.repository;

import com.george.fluffy.foxy.server.palladium.model.TaskPalladium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskPalladiumRepository extends CrudRepository<TaskPalladium, Long> {
    List<TaskPalladium> findByZoneLike(String zone);
    List<TaskPalladium> findByCreatorIdLike(int creatorId);
}