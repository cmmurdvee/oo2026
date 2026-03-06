package ee.murdvee.kontrolltoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(ex.getMessage());
        msg.setStatus(HttpStatus.BAD_REQUEST.value());
        msg.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }
}