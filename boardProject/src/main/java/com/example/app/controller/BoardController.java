package com.example.app.controller;

import com.example.app.domain.vo.BoardDTO;
import com.example.app.domain.vo.BoardVO;
import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.PageDTO;
import com.example.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    private final BoardService boardService;

//    게시글 목록
    @GetMapping("/list")
    public void list(Criteria criteria, Model model){
        if(criteria.getPage() == 0){
            criteria.create(1, 10);
        }
        model.addAttribute("boards", boardService.showAll(criteria));
        model.addAttribute("pagination",new PageDTO().createPageDTO(criteria, boardService.getTotal()));
    }

//    게시글 등록
    @GetMapping("/write")
    public void write(Criteria criteria, Model model){
        model.addAttribute("board", new BoardVO());
    }

    @PostMapping("/write")
//    화면이 아닌 다른 컨트롤러로 이동해야 할 때에는 RedirectView를 사용하여 Redirect방식으로 전송할 수 있다.
    public RedirectView write(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        boardService.register(boardDTO);
//        Redirect방식일 때 데이터를 전달하는 방법
//        1. Query String : 다음 컨트롤러에 데이터 전달
//        2. Flash : 화면에 데이터 전달
//        Session의 Flash영역을 사용하여 request가 초기화된 뒤 Flash영역에 담아뒀던 데이터를 꺼내올 수 있다.

//        RedirectAttributes는 addAttribute()를 사용하여 쿼리 스트링을 제작해주고,
//        addFlashAttribute()를 사용하여 Session의 Flash영역을 사용하게 해준다.
        redirectAttributes.addFlashAttribute("boardNumber", boardDTO.getBoardNumber());
        return new RedirectView("/board/list");
    }

//    게시글 상세보기
    @GetMapping(value = {"/read", "/update"})
    public void read(Long boardNumber, Criteria criteria, Model model){
        model.addAttribute("board", boardService.show(boardNumber));
    }

//    게시글 수정
    @PostMapping("/update")
    public RedirectView update(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        boardService.modify(boardDTO);
        redirectAttributes.addAttribute("boardNumber", boardDTO.getBoardNumber());
        return new RedirectView("/board/read");
    }

//    게시글 삭제
    @GetMapping("/delete")
    public RedirectView delete(Long boardNumber){
        boardService.remove(boardNumber);
        return new RedirectView("/board/list");
    }

    @GetMapping("/test")
    public String test(){
        return "/test";
    }
}























