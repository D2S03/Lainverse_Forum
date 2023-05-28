package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;
import com.imageBoardAI.boardai.Services.ImgurService;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostRepository postRepository;
    private final ImgurService imgurService;

    private ReplyRepository replyRepository;

    private ChatgptService chatgptService;

    @Autowired
    public PostController(PostRepository postRepository, ImgurService imgurService, ReplyRepository replyRepository, ChatgptService chatgptService) {
        this.postRepository = postRepository;
        this.imgurService = imgurService;
        this.replyRepository = replyRepository;
        this.chatgptService = chatgptService;
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
    public String uploadThread(@RequestParam("file") MultipartFile file,
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

    @PostMapping("{id}/CreateReply")
    public String uploadReply(@PathVariable("id") Integer id,
                              @RequestParam("message") String message,
                              @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        Reply reply = new Reply();
        reply.setMessege(message);
        reply.setDateTime(LocalDateTime.now());
        reply.setPost(post);

        if (file != null && !file.isEmpty()) {
            File imageFile = convertMultipartFileToFile(file);
            String imageUrl = imgurService.uploadImage(imageFile);
            reply.setImageUrl(imageUrl);
        }
        if (message.contains("bot")) {
            reply.setAuthor("bot");
        } else {
            reply.setAuthor("user");
        }

        if (file != null && !file.isEmpty()) {
            File imageFile = convertMultipartFileToFile(file);
            String imageUrl = imgurService.uploadImage(imageFile);
            reply.setImageUrl(imageUrl);
        }
        replyRepository.save(reply);

        return "redirect:/posts/thread/" + id;
    }

    @PostMapping("{id}/GPT_Judge")
    public String judgeGPT(@PathVariable("id") Integer id,
                           @RequestParam("message_id1") int messageId1,
                           @RequestParam("message_id2") int messageId2
    ) {

        String message1 = replyRepository.getReferenceById(messageId1).getMessege();
        String message2 = replyRepository.getReferenceById(messageId2).getMessege();
        Post Setpost = postRepository.getReferenceById(id);
        StringBuilder titleAndContext =  new StringBuilder();
titleAndContext.append(Setpost.getTitle());
titleAndContext.append("/" + Setpost.getMessege());
        List<MultiChatMessage> messages = Arrays.asList(
                new MultiChatMessage("assistant", "From the following two inputs from user1 (the first user input) and user2 (the second user input) i want you to decide which one is correct"),
               new MultiChatMessage("user","This is the Thread Title and after the title,separate with a slash '/' is the thread comment that the thread creator added to the title.It presents the main topic of discussion" + titleAndContext),
                new MultiChatMessage("user","user 1 = " + message1),
                new MultiChatMessage("user", "user 2 = " + message2));

    String responseMessage = chatgptService.multiChat(messages);
    Reply GPTreply = new Reply();
            GPTreply.setAuthor("bot");
            GPTreply.setPost(Setpost);
            GPTreply.setMessege(responseMessage);
            GPTreply.setDateTime(LocalDateTime.now());

        replyRepository.save(GPTreply);

        return "redirect:/posts/thread/" + id;
    }





        private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }



}


//make the thread name into a button where when you click it chatgpt generates a short explanation about the material
    //integrate reverse image search.

