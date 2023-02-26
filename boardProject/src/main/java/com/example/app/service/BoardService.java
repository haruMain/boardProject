package com.example.app.service;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    //    추가
    public void register(BoardDTO boardDTO);
    //    수정
    public void modify(BoardDTO boardDTO);
    //    삭제
    public void remove(Long boardNumber);
    //    조회
    public BoardDTO show(Long boardNumber);
    //    전체 조회
    public List<BoardVO> showAll(Criteria criteria);
//    전체 개수
    public int getTotal();
}
