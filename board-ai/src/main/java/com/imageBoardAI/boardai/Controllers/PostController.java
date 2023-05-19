package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.Entety.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping()
    public String getAllPosts(Model model) {
        List<Post> thePostsList = this.postRepository.findAll();
        model.addAttribute("posts", thePostsList);
        return "testFile";
    }

    @GetMapping("/thread/{id}")
    public String getThreadPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("post",postRepository.getReferenceById(id));
        return "individualPage";
    }

}

