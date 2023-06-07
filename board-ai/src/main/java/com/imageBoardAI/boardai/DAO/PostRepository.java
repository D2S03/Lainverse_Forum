package com.imageBoardAI.boardai.DAO;

import com.imageBoardAI.boardai.Entety.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> { }
