package com.example.app.repository;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import com.example.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;
    //    추가
    public void save(BoardDTO boardDTO){
        boardMapper.insert(boardDTO);
    }
    //    수정
    public void setBoardVO(BoardDTO boardDTO){
        boardMapper.update(boardDTO);
    }
    //    삭제
    public void remove(Long boardNumber){
        boardMapper.delete(boardNumber);
    }
    //    조회
    public BoardVO findById(Long boardNumber){
        return boardMapper.select(boardNumber);
    }
    //    전체 조회
    public List<BoardVO> findAll(Criteria criteria){
        return boardMapper.selectAll(criteria);
    }
//    전체 개수
    public int findCountAll(){
        return boardMapper.getTotal();
    }

}
