package com.imageBoardAI.boardai.Entety;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "messege")
    private String messege;
    @Column(name = "image_url")
    private String imageUrl; //make it so that it can be null??
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    public Reply() {}

}