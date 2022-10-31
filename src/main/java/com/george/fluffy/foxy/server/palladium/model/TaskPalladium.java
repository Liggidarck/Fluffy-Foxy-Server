package com.george.fluffy.foxy.server.palladium.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * zone:
 * - ostSchool
 * - barSchool
 * - etc
 */

@Entity
@Table(name = "task_palladium")
@Getter
@Setter
public class TaskPalladium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String zone;
    private String status;

    private String name;
    private String comment;

    private String address;
    private String floor;
    private String cabinet;
    private String letter;
    private boolean urgent;
    private String dateDone;

    private int executorId;
    private int creatorId;

    private String dateCreate;
    private String image;


}