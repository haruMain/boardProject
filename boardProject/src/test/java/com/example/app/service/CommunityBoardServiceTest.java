package com.example.app.service;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class CommunityBoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void registerTest(){
        BoardDTO boardDTO = new BoardDTO();
        FileVO file1 = new FileVO();
        FileVO file2 = new FileVO();

        file1.create("파일 이름1", "2022/11/16", UUID.randomUUID().toString(), true);
        file2.create("파일 이름2", "2022/11/17", UUID.randomUUID().toString(), false);

        List<FileVO> files = new ArrayList<>(Arrays.asList(file1, file2));

        boardDTO.create("첨부파일 테스트", "홍길동", "새로 작성한 게시글 내용");
        boardDTO.setFiles(files);

        boardService.register(boardDTO);
    }

    @Test
    public void modifyTest(){
        BoardDTO boardDTO = boardService.show(1L);
        boardDTO.setBoardTitle("수정된 게시글 제목");
        boardService.modify(boardDTO);
    }

    @Test
    public void showTest(){
        log.info("board: " + boardService.show(1L));
    }

    @Test
    public void showAllTest(){
        boardService.showAll(new Criteria().create(1, 10)).stream().map(BoardVO::getBoardTitle).forEach(log::info);
    }
}