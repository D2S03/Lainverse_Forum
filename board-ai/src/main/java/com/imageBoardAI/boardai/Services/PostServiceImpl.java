package com.imageBoardAI.boardai.Services;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.Entety.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService{
private PostRepository postRepository;

@Autowired
public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
}


    @Override
    public List<Post> getAllPosts() {
       List<Post> posts = postRepository.findAll();
       return posts;
    }

    @Override
    public void uploadPost(Post uploadPost) {
postRepository.save(uploadPost);
    }

    @Override
    public void deletePost(int postID) {
Post post = postRepository.getReferenceById(postID);
postRepository.delete(post);
    }


    @Override
    public Post findPostByID(int findID) {
    return postRepository.getReferenceById(findID);
    }
}
