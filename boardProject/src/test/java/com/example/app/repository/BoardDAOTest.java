package com.example.app.repository;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDAOTest {

    @Autowired
    private BoardDAO boardDAO;

    @Test
    public void saveTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.create("새로 작성한 게시글 제목", "홍길동", "새로 작성한 게시글 내용");
        boardDAO.save(boardDTO);
    }

    @Test
    public void setBoardVOTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.create(boardDAO.findById(1L));
        boardDTO.setBoardTitle("수정된 게시글 제목");
        boardDAO.setBoardVO(boardDTO);
    }

    @Test
    public void findByIdTest(){
        log.info("board: " + boardDAO.findById(1L));
    }

    @Test
    public void findAllTest(){
        boardDAO.findAll(new Criteria().create(1, 10)).stream().map(BoardVO::getBoardTitle).forEach(log::info);
    }
}












