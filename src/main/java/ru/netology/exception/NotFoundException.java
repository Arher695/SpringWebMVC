package ru.netology.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    //Когда пост не найден — сервис кидает NotFoundException.
    //Spring перехватывает RuntimeException → возвращает статус 500 Internal Server Error.
    //
    //Чтобы вернуть 404 Not Found, можно добавить @ControllerAdvice, но сейчас это не критично.
}
