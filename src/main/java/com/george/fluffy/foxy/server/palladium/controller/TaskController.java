package com.george.fluffy.foxy.server.palladium.controller;


import com.george.fluffy.foxy.server.palladium.repository.TaskPalladiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/foxy/palladium/tasks")
public class TaskController {

    @Autowired
    TaskPalladiumRepository taskRepository;


}
