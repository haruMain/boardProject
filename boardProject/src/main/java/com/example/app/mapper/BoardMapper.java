package com.example.app.mapper;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//    추가
    public void insert(BoardDTO boardDTO);
//    수정
    public void update(BoardDTO boardDTO);
//    삭제
    public void delete(Long boardNumber);
//    조회
    public BoardVO select(Long boardNumber);
//    전체 조회
    public List<BoardVO> selectAll(Criteria criteria);
//    전체 개수
    public int getTotal();

}
