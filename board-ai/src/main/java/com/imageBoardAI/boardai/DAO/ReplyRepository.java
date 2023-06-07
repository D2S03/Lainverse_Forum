package com.imageBoardAI.boardai.DAO;

import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
    List<Reply> findByPost(Post post);


}
