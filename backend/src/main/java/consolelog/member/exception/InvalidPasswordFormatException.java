package consolelog.member.exception;

import consolelog.global.advice.BadRequestException;

public class InvalidPasswordFormatException extends BadRequestException {

    private static final String MESSAGE = "올바른 비밀번호 형식이 아닙니다.";

    public InvalidPasswordFormatException() {
        super(MESSAGE);
    }
}
