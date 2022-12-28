package com.george.server.palladium.repository;

import com.george.server.palladium.model.TaskPalladium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskPalladiumRepository extends CrudRepository<TaskPalladium, Long> {
    List<TaskPalladium> findByZoneLike(String zone);

    long countByZoneLikeAndStatusLike(String zone, String status);

    long countByZoneLike(String zone);
    List<TaskPalladium> getByZoneLikeAndStatusLikeAndExecutorId(String zone, String status, int executorId);

    List<TaskPalladium> getByZoneLikeAndStatusLike(String zone, String status);
    List<TaskPalladium> findByStatus(String status);
    List<TaskPalladium> getByCreatorId(int creatorId);

    List<TaskPalladium> getByExecutorId(int executorId);



}