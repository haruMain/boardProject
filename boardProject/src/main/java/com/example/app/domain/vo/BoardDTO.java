package com.example.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardDTO {
    private Long boardNumber;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;

    private List<FileVO> files;

    public void create(BoardVO boardVO) {
        this.boardNumber = boardVO.getBoardNumber();
        this.boardTitle = boardVO.getBoardTitle();
        this.boardWriter = boardVO.getBoardWriter();
        this.boardContent = boardVO.getBoardContent();
        this.boardRegisterDate = boardVO.getBoardRegisterDate();
        this.boardUpdateDate = boardVO.getBoardUpdateDate();
    }

    public void create(String boardTitle, String boardWriter, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContent = boardContent;
    }

    public void create(String boardTitle, String boardWriter, String boardContent, List<FileVO> files) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContent = boardContent;
        this.files = files;
    }
}
