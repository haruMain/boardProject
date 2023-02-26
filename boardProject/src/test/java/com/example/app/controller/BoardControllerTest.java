package com.example.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class BoardControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

//    브라우저에서 URL을 요청한 것과 같은 환경을 구성해준다.
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void list() throws Exception{
        log.info("boards: " + mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("page", "1")
                .param("amount", "10")
        ).andReturn().getModelAndView().getModelMap());
    }

    @Test
    void write() throws Exception{
        log.info("flash map: " + mockMvc.perform(MockMvcRequestBuilders.post("/board/write")
            .param("boardTitle", "새로 작성한 글 제목")
            .param("boardContent", "새로 작성한 글 내용")
            .param("boardWriter", "TEST")).andReturn().getFlashMap());
    }

    @Test
    void read() throws Exception {
        log.info("model map: " + mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("boardNumber", "22"))
                .andReturn().getModelAndView().getModelMap());
    }

    @Test
    void update() throws Exception{
        log.info("model map: " + mockMvc.perform(MockMvcRequestBuilders.post("/board/update")
                .param("boardNumber", "22")
                .param("boardTitle", "수정된 게시글 제목")
                .param("boardContent", "수정된 게시글 내용")
        ).andReturn().getModelAndView().getModelMap());
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/board/delete")
            .param("boardNumber", "22")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}










