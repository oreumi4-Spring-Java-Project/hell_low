package com.est.helllow.service;

import com.est.helllow.domain.Reply;
import com.est.helllow.dto.ReplyDTO;
import com.est.helllow.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyreplyService {
    @Autowired
    ReplyRepository replyRepository;

    public List<ReplyDTO> getMyReplys (String userId) {
        List<Reply> replyList = replyRepository.findByUser_userId(userId);
        List<ReplyDTO> replyDTOList = replyList.stream().map((reply) -> ReplyDTO.toDTO(reply)).collect(Collectors.toList());
        return replyDTOList;
    }
}
