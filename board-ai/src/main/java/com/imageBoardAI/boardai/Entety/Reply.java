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
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Reply() {}

    public Reply(int id, String messege, String imageUrl, LocalDateTime dateTime, Post post) {
        this.id = id;
        this.messege = messege;
        this.imageUrl = imageUrl;
        this.dateTime = dateTime;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
