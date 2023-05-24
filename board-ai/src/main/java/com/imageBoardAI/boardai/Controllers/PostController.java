package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;
import com.imageBoardAI.boardai.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostRepository postRepository;
    ReplyRepository replyRepository;
    ImageService imageService;

    @Autowired
    public PostController(PostRepository postRepository, ReplyRepository replyRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
        this.imageService = imageService;
    }





    @GetMapping()
    public String getAllPosts(Model model) {
        List<Post> thePostsList = this.postRepository.findAll();
        model.addAttribute("posts", thePostsList);
        return "testFile";
    }


    @GetMapping("/thread/{id}")
    public String getThreadPage(@PathVariable("id") int id, Model model) {
        Post post = postRepository.getReferenceById(id);
        List<Reply> replies = post.getReplies();
        model.addAttribute("post", post);
        model.addAttribute("replies", replies);
        return "individualPage";
    }
    @PostMapping("/{id}/image")
    public String uploadImage(@PathVariable("id") int id, @RequestParam("image") MultipartFile imageFile, Model model) {
        try {
            Post post = postRepository.getReferenceById(id);
            String imageUrl = imageService.saveImage(imageFile);
            post.setImageURL(imageUrl);
            postRepository.save(post);
        } catch (IOException | S3Exception e) {
            // Handle error case
        }

        return "redirect:/posts/thread/" + id;
    }





}
























//make the thread name into a button where when you click it chagpt generates a short explaination about the material
    //integrate reverse image searcg


