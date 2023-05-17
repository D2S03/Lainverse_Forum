package com.imageBoardAI.boardai.Services;

import com.imageBoardAI.boardai.DAO.PostRepository;
import com.imageBoardAI.boardai.Entety.Post;

import java.util.List;

public interface PostService {

List<Post> getAllPosts();

void uploadPost(Post uploadPost);
void deletePost(int postID);
Post findPostByID(int findID);
}
