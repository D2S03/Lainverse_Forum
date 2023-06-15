package com.imageBoardAI.boardai.Services;

import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Entety.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

private ReplyRepository replyRepository;

@Autowired
public ReplyService(ReplyRepository replyRepository){
    this.replyRepository = replyRepository;
}
    public List<Reply> getAllReplies() {
    List<Reply> replies = replyRepository.findAll();
    return replies;
    }

    public void uploadReply(Reply uploadReply) {
replyRepository.save(uploadReply);
    }

    public void deleteReply(int replyID) {
replyRepository.deleteById(replyID);
    }

    public Optional<Reply> findReplyByID(int replyID) {
        Optional<Reply> reply = replyRepository.findById(replyID);
          return reply;
    }
}
