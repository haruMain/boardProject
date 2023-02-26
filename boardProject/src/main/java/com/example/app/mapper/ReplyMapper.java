package com.example.app.mapper;

import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    public void insert(ReplyVO replyVO);
    public List<ReplyVO> selectAll(@Param("boardNumber") Long boardNumber, @Param("criteria") Criteria criteria);
    public void update(ReplyVO replyVO);
    public void delete(Long replyNumber);
    public ReplyVO select(Long replyNumber);
    public int getTotal(Long boardNumber);
}
