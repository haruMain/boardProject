package com.example.app.controller;

import com.example.app.domain.vo.Criteria;
import com.example.app.domain.vo.ReplyDTO;
import com.example.app.domain.vo.ReplyVO;
import com.example.app.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply/*")
public class ReplyController {
    private final ReplyService replyService;

//    추가
    @PostMapping("/new")
    public String write(@RequestBody ReplyVO replyVO){
        replyService.register(replyVO);
        return "write success";
    }

//    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
//    public ResponseEntity<String> write(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {
//        replyService.register(replyVO);
//        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
//    }

//    전체 조회
    @GetMapping("/list/{boardNumber}/{page}")
    public ReplyDTO list(@PathVariable("boardNumber") Long boardNumber, @PathVariable int page){
        return new ReplyDTO(replyService.showAll(boardNumber, new Criteria().create(page, 10)), replyService.count(boardNumber));
    }

//    수정
//    @PatchMapping(value = {"/{rno}", "/{rno}/{replyWriter}"})
////    @PutMapping(value = {"/{rno}", "/{rno}/{replyWriter}"})
//    public void modify(@RequestBody ReplyVO replyVO, @PathVariable("rno") Long replyNumber, @PathVariable(value = "replyWriter", required = false) String replyWriter){
//                                                                  null도 허용하기 위해 default인 true를 false로 변경하였다.
//        replyVO.setReplyNumber(replyNumber);
//        replyVO.setReplyWriter(Optional.ofNullable(replyWriter).orElse(replyService.show(replyNumber).getReplyWriter()));
//        replyService.modify(replyVO);
//    }


    @PostMapping("/modify")
    public void modify(@RequestBody ReplyVO replyVO){
//        Optional은 JDK8부터 지원되는 검증 클래스이며, ofNullable(Object)로 사용한다.
//        ifPresent()를 사용하여 null 여부 검사가 가능하며, null이 아니라면 기존 Object 객체를 그대로 사용한다.
//        orElse를 사용하면 null일 경우 대체할 객체를 작성할 수 있다.
        replyVO.setReplyWriter(Optional.ofNullable(replyVO.getReplyWriter()).orElse(replyService.show(replyVO.getReplyNumber()).getReplyWriter()));
        replyService.modify(replyVO);
    }

//    댓글 삭제
    @DeleteMapping("/{replyNumber}")
    public void remove(@PathVariable Long replyNumber){
        replyService.remove(replyNumber);
    }

//    댓글 1개 조회
    @GetMapping("/{replyNumber}")
    public ReplyVO show(@PathVariable Long replyNumber){
        return replyService.show(replyNumber);
    }
}























