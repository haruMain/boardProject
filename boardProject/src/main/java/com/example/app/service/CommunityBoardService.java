package com.example.app.service;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.FileVO;
import com.example.app.repository.BoardDAO;
import com.example.app.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor @Qualifier("community") @Primary
public class CommunityBoardService implements BoardService {
    private final BoardDAO boardDAO;
    private final FileDAO fileDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(BoardDTO boardDTO) {
        boardDAO.save(boardDTO);
        List<FileVO> files = boardDTO.getFiles();
//        Optional : 검증
        Optional.ofNullable(files).ifPresent(fileList -> {
            fileList.forEach(file -> {
                file.setBoardNumber(boardDTO.getBoardNumber());
                fileDAO.save(file);
            });
        });
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        boardDAO.setBoardVO(boardDTO);
        fileDAO.remove(boardDTO.getBoardNumber());
        List<FileVO> files = boardDTO.getFiles();
        Optional.ofNullable(files).ifPresent(fileList -> {
            fileList.forEach(file -> {
                file.setBoardNumber(boardDTO.getBoardNumber());
                fileDAO.save(file);
            });
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long boardNumber) {
        fileDAO.remove(boardNumber);
        boardDAO.remove(boardNumber);
    }

    @Override
    public BoardDTO show(Long boardNumber) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.create(boardDAO.findById(boardNumber));
        boardDTO.setFiles(fileDAO.findAll(boardNumber));
        return boardDTO;
    }

    @Override
    public List<BoardVO> showAll(Criteria criteria) {
        return boardDAO.findAll(criteria);
    }

    @Override
    public int getTotal() {
        return boardDAO.findCountAll();
    }
}
