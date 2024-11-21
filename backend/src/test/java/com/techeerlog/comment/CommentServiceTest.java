package com.techeerlog.comment;

import com.techeerlog.auth.dto.AuthInfo;
import com.techeerlog.comment.service.CommentService;
import com.techeerlog.dummy.service.DummyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private DummyService dummyService;

    @Autowired
    private CommentService commentService;

    @Test
    void setUp(){
        dummyService.insertDummy();

    }
    @Test
    @Transactional
    void findCommentsTest(){
        commentService.findComments(1L, new AuthInfo(1L, "type", "nickname"));
    }

}
