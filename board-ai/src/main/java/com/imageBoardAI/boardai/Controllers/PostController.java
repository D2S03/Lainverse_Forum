package com.imageBoardAI.boardai.Controllers;

import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;
import com.imageBoardAI.boardai.Services.ImgurService;
import com.imageBoardAI.boardai.Services.PostService;
import com.imageBoardAI.boardai.Services.ReplyService;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final ImgurService imgurService;
    private PostService postService;
    private ChatgptService chatgptService;
    private ReplyService replyService;

    @Autowired
    public PostController(ImgurService imgurService, PostService postService, ChatgptService chatgptService, ReplyService replyService) {
        this.imgurService = imgurService;
        this.postService = postService;
        this.chatgptService = chatgptService;
        this.replyService = replyService;
    }

    @GetMapping()
    public String getAllPosts(Model model) {
        List<Post> thePostsList = postService.getAllPosts();
        model.addAttribute("posts", thePostsList);
        return "cataloguePage";
    }

    @GetMapping("/thread/{id}")
    public String getThreadPage(@PathVariable("id") int id, Model model) {
        Post post = postService.findPostByID(id);
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
            // Convert MultipartFile to File and uploads the image to imgur.Then saves the imageURL and other data to the database.
            File imageFile = convertMultipartFileToFile(file);
            String imageUrl = imgurService.uploadImage(imageFile);
            Post post = new Post();
            post.setImageURL(imageUrl);
            post.setTitle(title);
            post.setMessege(message);
            post.setDateTime(LocalDateTime.now());
            postService.uploadPost(post);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image and create thread", e);
        }
        return "redirect:/posts";
    }

    @PostMapping("{id}/CreateReply")
    public String uploadReply(@PathVariable("id") Integer id,
                              @RequestParam("message") String message,
                              @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        Post post = postService.findPostByID(id);

        Reply reply = new Reply();
        reply.setMessege(message);
        reply.setDateTime(LocalDateTime.now());
        reply.setPost(post);

        if (file != null && !file.isEmpty()) {
            File imageFile = convertMultipartFileToFile(file);
            String imageUrl = imgurService.uploadImage(imageFile);
            reply.setImageUrl(imageUrl);
        }
        isBot(message, reply);
        replyService.uploadReply(reply);

        return "redirect:/posts/thread/" + id;
    }

    @PostMapping("{id}/GPT_Judge")
    public String judgeGPT(@PathVariable("id") Integer id,
                           @RequestParam("message_id1") int messageId1,
                           @RequestParam("message_id2") int messageId2
    ) {
        Optional<Reply> reply1 = replyService.findReplyByID(messageId1);
        Optional<Reply> reply2 = replyService.findReplyByID(messageId2);

        String message1 = reply1.get().getMessege();
        String message2 = reply2.get().getMessege();
        Post Setpost = postService.findPostByID(id);
        Reply GPTreply = createGPT(Setpost, message1, message2);
        replyService.uploadReply(GPTreply);
        return "redirect:/posts/thread/" + id;
    }


    private void isBot(String message, Reply reply) {
        //this function sets the "author" field to bot or user,depending no wether the messege is written by GPT or is a human reply.This is later used to determine the post texts' color.
        if (message.contains("bot")) {
            reply.setAuthor("bot");
        } else {
            reply.setAuthor("user");
        }
    }


    private Reply createGPT(Post Setpost, String message1, String message2) {
        //Takes in the title and messages for the GPT to use by getting them from the post and then adding the messages to the MultiChatMessage list for ChatGPT to process,alongside the necessary context.
        StringBuilder titleAndContext = new StringBuilder();
        titleAndContext.append(Setpost.getTitle());
        titleAndContext.append("/" + Setpost.getMessege());
        List<MultiChatMessage> messages = Arrays.asList(
                new MultiChatMessage("assistant", "From the following two inputs from user1 (the first user input) and user2 (the second user input.I want you to search trough the internet and cite your sources."),
                new MultiChatMessage("user", "This is the Thread Title and after the title,separate with a slash '/' is the thread comment that the thread creator added to the title.It presents the main topic of discussion" + titleAndContext),
                new MultiChatMessage("user", "user 1 = " + message1),
                new MultiChatMessage("user", "user 2 = " + message2));
        String responseMessage = chatgptService.multiChat(messages);
        Reply GPTreply = new Reply();
        //finally it sets the details to a reply and posts it.
        GPTreply.setAuthor("bot");
        GPTreply.setPost(Setpost);
        GPTreply.setMessege(responseMessage);
        GPTreply.setDateTime(LocalDateTime.now());

        return GPTreply;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        // Create a File object with a unique name in the temporary directory
        File convertedFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
        //Converts a MultipartFile to a File object
    }
}




