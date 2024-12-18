package uni.project.rest.api.exception;

import java.io.Serial;

public class BadRequestException extends org.apache.coyote.BadRequestException
{
    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable throwable) {
        super(throwable);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
