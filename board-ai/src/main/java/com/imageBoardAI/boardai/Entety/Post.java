package com.imageBoardAI.boardai.Entety;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_table")
public class Post {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="messege")
    private String messege;
    @Column(name="image_url")

    private String imageURL;
    @Column(name="reply_id")
    private int replyId;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Post() {}
    public Post(Long id, String title, String messege, String imageURL, int replyId,LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.messege = messege;
        this.imageURL = imageURL;
        this.replyId = replyId;
        this.dateTime = dateTime;
    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

