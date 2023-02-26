package com.example.app.repository;

import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.ReplyVO;
import com.example.app.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

    public void save(ReplyVO replyVO){
        replyMapper.insert(replyVO);
    }

    public List<ReplyVO> findAll(Long boardNumber, Criteria criteria){
        return replyMapper.selectAll(boardNumber, criteria);
    }

    public void setReplyVO(ReplyVO replyVO){
        replyMapper.update(replyVO);
    }

    public void remove(Long replyNumber){
        replyMapper.delete(replyNumber);
    }

    public ReplyVO findById(Long replyNumber){
        return replyMapper.select(replyNumber);
    }

    public int count(Long boardNumber){
        return replyMapper.getTotal(boardNumber);
    }
}





















