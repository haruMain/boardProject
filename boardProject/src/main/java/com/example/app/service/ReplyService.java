package com.example.app.service;

import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.ReplyVO;
import com.example.app.repository.ReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

    public void register(ReplyVO replyVO){
        replyDAO.save(replyVO);
    }

    public List<ReplyVO> showAll(Long boardNumber, Criteria criteria){
        return replyDAO.findAll(boardNumber, criteria);
    }

    public void modify(ReplyVO replyVO){
        replyDAO.setReplyVO(replyVO);
    }

    public void remove(Long replyNumber){
        replyDAO.remove(replyNumber);
    }

    public ReplyVO show(Long replyNumber){
        return replyDAO.findById(replyNumber);
    }

    public int count(Long boardNumber){
        return replyDAO.count(boardNumber);
    }
}
