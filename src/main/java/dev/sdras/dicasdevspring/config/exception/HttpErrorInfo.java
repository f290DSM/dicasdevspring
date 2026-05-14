package dev.sdras.dicasdevspring.config.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
public class HttpErrorInfo {
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private String path;
    private HttpStatus httpStatus;
    private String message;

    public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
        this.httpStatus = httpStatus;
        this.path = path;
        this.message = message;
    }
}
