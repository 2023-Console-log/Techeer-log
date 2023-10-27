package consolelog.comment.exception;

import consolelog.advice.BadRequestException;

public class ReplyDepthException extends BadRequestException {

    private static final String MESSAGE = "대댓글에 답글을 달 수 없습니다.";

    public ReplyDepthException() {
        super(MESSAGE);
    }
}