package com.george.fluffy.foxy.server.palladium.repository;

import com.george.fluffy.foxy.server.palladium.model.TaskPalladium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskPalladiumRepository extends CrudRepository<TaskPalladium, Long> {
    List<TaskPalladium> findByZoneLike(String zone);

    List<TaskPalladium> getByZoneLikeAndStatusLike(String zone, String status);
    List<TaskPalladium> findByStatus(String status);
    List<TaskPalladium> getByCreatorId(int creatorId);

    List<TaskPalladium> getByExecutorId(int executorId);



}