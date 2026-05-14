package dev.sdras.dicasdevspring.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody HttpErrorInfo handleBadRequestException(HttpServletRequest request, BadRequestException e) {
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, request, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody HttpErrorInfo handleNotFoundException(HttpServletRequest request, NotFoundException e) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request, e);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    @ExceptionHandler(InvalidInputException.class)
    public @ResponseBody HttpErrorInfo handleUnprocessableContentException(HttpServletRequest request, InvalidInputException e) {  // Fixed parameter type
        return createHttpErrorInfo(HttpStatus.UNPROCESSABLE_CONTENT, request, e);
    }

    public HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest request, Exception e) {
        final String path = request.getRequestURI();  // Updated for servlet
        final String message = e.getMessage();
        log.debug("Retorno HTTP Status: {}, Path: {}, Message: {}", httpStatus.value(), path, message);
        return new HttpErrorInfo(httpStatus, path, message);
    }
}