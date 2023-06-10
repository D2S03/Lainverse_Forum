package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Services.ImgurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replies")
public class ReplyController {
   private ReplyRepository replyRepository;
   private PostRepository postRepository;
     private final ImgurServiceImpl imgurService;
     @Autowired
    public ReplyController(ReplyRepository replyRepository, PostRepository postRepository, ImgurServiceImpl imgurService) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
        this.imgurService = imgurService;
    }


}
