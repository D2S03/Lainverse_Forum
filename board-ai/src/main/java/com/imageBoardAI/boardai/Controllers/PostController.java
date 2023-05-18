package com.imageBoardAI.boardai.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping()
    public String pageRet(Model model){
        model.addAttribute("something","this is coming from the controller");
        return "testFile";
    }



}
