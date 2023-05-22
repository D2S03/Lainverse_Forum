package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Entety.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replies")
public class ReplyController {
    ReplyRepository replyRepository;
@Autowired
    public ReplyController(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }





}
