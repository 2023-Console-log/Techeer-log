package consolelog.advice;

public class ForbiddenException extends BusinessException {

    public ForbiddenException(String message) {
        super(message);
    }
}