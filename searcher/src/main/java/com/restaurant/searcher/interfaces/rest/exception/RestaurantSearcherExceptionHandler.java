package com.restaurant.searcher.interfaces.rest.exception;

import com.restaurant.searcher.domain.exception.badrequest.AbstractBadRequestException;
import com.restaurant.searcher.domain.exception.internalserver.AbstractInternalServerErrorException;
import com.restaurant.searcher.domain.model.rest.response.ExceptionRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestaurantSearcherExceptionHandler extends ResponseEntityExceptionHandler {

    private ExceptionRestResponse getExceptionResponse(HttpStatus httpStatus, RuntimeException exception){
        return  ExceptionRestResponse
                .builder()
                .httpStatusDescription(httpStatus.getReasonPhrase())
                .httpStatusCode(httpStatus.value())
                .description(exception.getMessage())
                .build();
    }

    @ExceptionHandler(value= {AbstractBadRequestException.class})
    public final ResponseEntity<ExceptionRestResponse> handleBadRequestException(AbstractBadRequestException ex, WebRequest request){

        var httpStatus = HttpStatus.BAD_REQUEST;
        var exceptionRestResponse = this.getExceptionResponse(httpStatus, ex);
        return new ResponseEntity<>(exceptionRestResponse, httpStatus);
    }

    @ExceptionHandler(value= {AbstractInternalServerErrorException.class})
    public final ResponseEntity<ExceptionRestResponse> handleAbstractInternalServerErrorException(AbstractInternalServerErrorException ex, WebRequest request){
        return handleInternalServerError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ExceptionRestResponse> handleInternalServerError(HttpStatus internalServerError, RuntimeException ex) {
        return handleInternalServerError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(value= {RuntimeException.class})
    public final ResponseEntity<ExceptionRestResponse> handleRuntimeException(RuntimeException ex, WebRequest request){

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var exceptionRestResponse = this.getExceptionResponse(httpStatus, ex);
        return new ResponseEntity<>(exceptionRestResponse, httpStatus);
    }

}
