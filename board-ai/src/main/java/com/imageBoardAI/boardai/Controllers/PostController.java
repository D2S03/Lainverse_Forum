package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;
import com.imageBoardAI.boardai.Services.ImgurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostRepository postRepository;
    private final ImgurService imgurService;

    @Autowired
    public PostController(PostRepository postRepository, ImgurService imgurService) {
        this.postRepository = postRepository;
        this.imgurService = imgurService;
    }



    @GetMapping()
    public String getAllPosts(Model model) {
        List<Post> thePostsList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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



    @PostMapping("/createThread")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("title") String title,
                              @RequestParam("message") String message) {

        try {
            File imageFile = convertMultipartFileToFile(file);
            String imageUrl = imgurService.uploadImage(imageFile);
            Post post = new Post();
            post.setImageURL(imageUrl);
            post.setTitle(title);
            post.setMessege(message);
            post.setDateTime(LocalDateTime.now());
            postRepository.save(post);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image and create thread", e);
        }

        return "redirect:/posts";
    }


    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }



}


//make the thread name into a button where when you click it chagpt generates a short explaination about the material
    //integrate reverse image search


