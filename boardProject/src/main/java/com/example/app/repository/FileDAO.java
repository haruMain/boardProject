package com.example.app.repository;

import com.example.app.domain.vo.FileVO;
import com.example.app.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    //    파일 추가
    public void save(FileVO fileVO){
        fileMapper.insert(fileVO);
    }
    //    파일 삭제
    public void remove(Long boardNumber){
        fileMapper.delete(boardNumber);
    }
    //    파일 조회
    public List<FileVO> findAll(Long boardNumber){
        return fileMapper.selectAll(boardNumber);
    }
}
