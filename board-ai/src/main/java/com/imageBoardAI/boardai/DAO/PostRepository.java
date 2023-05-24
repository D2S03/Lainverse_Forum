package com.imageBoardAI.boardai.DAO;

import com.imageBoardAI.boardai.Entety.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> { }
