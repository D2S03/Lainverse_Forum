package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.Entety.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostRepository postRepository;
    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping()
    public String getAllPosts(Model model){
        Post post = new Post(1L,"Naruto is bad","Honestly i really do feel like naruto has fallen off","https://win.gg/wp-content/uploads/2022/06/naruto-and-sasuke.jpg",1,LocalDateTime.now());
        model.addAttribute("posts",post);

        return "testFile";
    }


}
