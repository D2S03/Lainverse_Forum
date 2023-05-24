package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.DAO.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replies")
public class ReplyController {
    ReplyRepository replyRepository;
    PostRepository postRepository;
@Autowired
    public ReplyController(ReplyRepository replyRepository, PostRepository postRepository) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
    }






}
