package com.imageBoardAI.boardai.Services;

import com.imageBoardAI.boardai.DAO.ReplyRepository;
import com.imageBoardAI.boardai.Entety.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService{

private ReplyRepository replyRepository;

@Autowired
public ReplyServiceImpl (ReplyRepository replyRepository){
    this.replyRepository = replyRepository;
}
    @Override
    public List<Reply> getAllReplies() {
    List<Reply> replies = replyRepository.findAll();
    return replies;
    }

    @Override
    public void uploadReply(Reply uploadReply) {
replyRepository.save(uploadReply);
    }

    @Override
    public void deleteReply(int replyID) {
replyRepository.deleteById(replyID);
    }

    @Override
    public Optional<Reply> findReplyByID(int replyID) {
        Optional<Reply> reply = replyRepository.findById(replyID);
          return reply;
    }
}
