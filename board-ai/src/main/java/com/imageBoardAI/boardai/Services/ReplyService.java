package com.imageBoardAI.boardai.Services;

import com.imageBoardAI.boardai.Entety.Post;
import com.imageBoardAI.boardai.Entety.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyService {

    List<Reply> getAllReplies();

    void uploadReply(Reply uploadReply);
    void deleteReply(int replyID);
    Optional<Reply> findReplyByID(int replyID);

}
