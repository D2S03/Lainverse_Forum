package com.imageBoardAI.boardai.Entety;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="reply_table")
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