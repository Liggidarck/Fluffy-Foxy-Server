package com.george.fluffy.foxy.server.palladium.controller;

import com.george.fluffy.foxy.server.auth.response.MessageResponse;
import com.george.fluffy.foxy.server.palladium.model.TaskPalladium;
import com.george.fluffy.foxy.server.palladium.repository.TaskPalladiumRepository;
import com.george.fluffy.foxy.server.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/palladium/tasks")
public class TaskController {

    @Autowired
    TaskPalladiumRepository taskRepository;


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')  ")
    public ResponseEntity<?> createTask(@RequestBody TaskPalladium task) {
        taskRepository.save(task);
        return ResponseEntity.ok(new MessageResponse("Task successfully created!"));
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editTask(@RequestBody TaskPalladium task,
                                      @RequestParam(value = "id") long id) {

        TaskPalladium updateTask = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );

        updateTask.setZone(task.getZone());
        updateTask.setStatus(task.getStatus());
        updateTask.setName(task.getName());
        updateTask.setComment(task.getComment());
        updateTask.setAddress(task.getAddress());
        updateTask.setFloor(task.getFloor());
        updateTask.setCabinet(task.getCabinet());
        updateTask.setLetter(task.getLetter());
        updateTask.setUrgent(task.isUrgent());
        updateTask.setDateDone(task.getDateDone());
        updateTask.setExecutorId(task.getExecutorId());
        updateTask.setCreatorId(task.getCreatorId());
        updateTask.setDateCreate(task.getDateCreate());
        updateTask.setImage(task.getImage());

        taskRepository.save(updateTask);

        return ResponseEntity.ok(new MessageResponse("Task successfully edited"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteTask(@RequestParam(value = "id") long id) {

        taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );

        taskRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Task successfully deleted"));
    }

    @GetMapping("/get/countByStatus")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> countByZoneLikeAndStatusLike(@RequestParam(value = "zone") String zone,
                                                          @RequestParam(value = "status") String status) {
        long count = taskRepository.countByZoneLikeAndStatusLike(zone, status);
        return ResponseEntity.ok(new MessageResponse("" + count));
    }

    @GetMapping("/get/countByZoneLike")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> countByZoneLike(@RequestParam(value = "zone") String zone) {
        long count = taskRepository.countByZoneLike(zone);
        return ResponseEntity.ok(new MessageResponse("" + count));
    }

    @GetMapping("/get/byZoneAndStatusAndExecutorIdLike")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN')")
    public List<TaskPalladium> getByZoneLikeAndStatusLikeAndExecutorId(@RequestParam(value = "zone") String zone,
                                                                       @RequestParam(value = "status") String status,
                                                                       @RequestParam(value = "executorId") int executorId) {
        return taskRepository.getByZoneLikeAndStatusLikeAndExecutorId(zone, status, executorId);
    }

    @GetMapping("/get/byExecutor")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_EXECUTOR')")
    public List<TaskPalladium> getTasksByExecutor(@RequestParam(value = "id") int id) {
        return taskRepository.getByExecutorId(id);
    }

    @GetMapping("/get/byCreator")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<TaskPalladium> getTasksByCreator(@RequestParam(value = "id") int id) {
        return taskRepository.getByCreatorId(id);
    }

    @GetMapping("/get/byStatus")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<TaskPalladium> getTasksByStatus(@RequestParam(value = "status") String status) {
        return taskRepository.findByStatus(status);
    }

    @GetMapping("/get/byZone")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<TaskPalladium> getTasksByZone(@RequestParam(value = "zone") String zone) {
        return taskRepository.findByZoneLike(zone);
    }

    @GetMapping("/get/byZoneAndStatus")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<TaskPalladium> getByZoneLikeAndStatusLike(@RequestParam(value = "zone") String zone,
                                                          @RequestParam(value = "status") String status) {
        return taskRepository.getByZoneLikeAndStatusLike(zone, status);
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<TaskPalladium> getAllTasks() {
        return (List<TaskPalladium>) taskRepository.findAll();
    }

    @GetMapping("/get/byId")
    @PreAuthorize("hasRole('ROLE_DEVELOPER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_EXECUTOR')")
    public TaskPalladium getTask(@RequestParam(value = "id") long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );
    }

}
