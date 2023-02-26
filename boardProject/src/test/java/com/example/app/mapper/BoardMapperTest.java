package com.example.app.mapper;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void insertTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.create("새로 작성한 게시글 제목", "홍길동", "새로 작성한 게시글 내용");
        boardMapper.insert(boardDTO);
        log.info("boardNumber: " + boardDTO.getBoardNumber());
    }

    @Test
    public void updateTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.create(boardMapper.select(1L));
        boardDTO.setBoardTitle("수정된 게시글 제목");
        boardMapper.update(boardDTO);
    }

    @Test
    public void selectTest(){
        log.info("board: " + boardMapper.select(1L));
    }

    @Test
    public void selectAllTest(){
        boardMapper.selectAll(new Criteria().create(1, 10)).stream().map(BoardVO::getBoardTitle).forEach(log::info);
    }
}


















