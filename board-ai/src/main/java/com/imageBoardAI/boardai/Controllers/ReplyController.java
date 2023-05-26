package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;
import com.imageBoardAI.boardai.Services.ImgurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/replies")
public class ReplyController {
   private ReplyRepository replyRepository;
   private PostRepository postRepository;
     private final ImgurService imgurService;
     @Autowired
    public ReplyController(ReplyRepository replyRepository, PostRepository postRepository, ImgurService imgurService) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
        this.imgurService = imgurService;
    }


}
