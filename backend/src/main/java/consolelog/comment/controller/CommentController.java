package consolelog.comment.controller;

import consolelog.auth.dto.AuthInfo;
import consolelog.comment.domain.Comment;
import consolelog.comment.dto.*;
import consolelog.comment.service.CommentService;
import consolelog.support.token.Login;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static consolelog.result.ResultCode.*;


@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<ResultResponse<CommentResponse>> addComment(@PathVariable(name = "id") Long postId,
                                                                      @Valid @RequestBody NewCommentRequest newCommentRequest,
                                                                      @Login AuthInfo authInfo) {
        Long commentId = commentService.addComment(postId, newCommentRequest, authInfo);
        ResultResponse<CommentResponse> resultResponse = new ResultResponse<CommentResponse>(COMMENT_CREATED_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    //대댓글
    @PostMapping("/comments/{id}/reply")
    public ResponseEntity<Void> addReply(@PathVariable(name = "id") Long commentId,
                                         @Valid @RequestBody NewReplyRequest newReplyRequest,
                                         @Login AuthInfo authInfo) {
        Long replyId = commentService.addReply(commentId, newReplyRequest, authInfo);
        return ResponseEntity.created(URI.create("/comments/" + replyId)).build();
    }


    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<ResultResponse> findComments(@PathVariable(name = "id") Long postId,
                                                       @Login AuthInfo authInfo) {
        CommentsResponse commentsResponse = commentService.findComments(postId, authInfo);
        ResultResponse<Comment> resultResponse = new ResultResponse<Comment>(GET_COMMENT_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<ResultResponse<CommentResponse>> updateComment(@PathVariable(name = "id") Long commentId,
                                                                         @Valid @RequestBody UpdateCommentRequest updateCommentRequest,
                                                                         @Login AuthInfo authInfo) {
        CommentResponse updatedComment = commentService.updateComment(commentId, updateCommentRequest, authInfo);
        ResultResponse<CommentResponse> resultResponse = new ResultResponse<CommentResponse>(UPDATE_COMMENT_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity<ResultResponse> deleteComment(@PathVariable(name = "id") Long commentId,
                                                        @Login AuthInfo authInfo) {
        commentService.deleteComment(commentId, authInfo);
        ResultResponse resultResponse = new ResultResponse<>(DELETE_COMMENT_SUCCESS);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultResponse);
    }
}
