package com.example.customerrelationshipmanager.exception.configuration;

import com.example.customerrelationshipmanager.exception.BadRequestException;
import com.example.customerrelationshipmanager.exception.CustomerNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,final HttpHeaders headers,final HttpStatus status,
                                                                   final WebRequest request) {
        final String error = "No handler found for "+ ex.getHttpMethod() + " " + ex.getRequestURL();

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex.getLocalizedMessage(),error);
        return buildResponseEntity(ex,apiError);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex,final HttpHeaders headers,final HttpStatus status,final WebRequest request) {
        final String error = ex.getValue() + " value for "+ex.getPropertyName()+" should be of type "+ ex.getRequiredType();
        final ApiError apiError =new ApiError(HttpStatus.BAD_REQUEST,ex.getLocalizedMessage(),error);
        return buildResponseEntity(ex,apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t+ " "));

        final ApiError apiError =new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
        return buildResponseEntity(ex,apiError);
    }

    // Custom Exception Handlers -> Add next ones after these methods.

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFound(final CustomerNotFoundException ex,final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex.getLocalizedMessage(),"error occured");
        return buildResponseEntity(ex,apiError);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(final BadRequestException ex,final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex.getLocalizedMessage(),"error occured");
        return buildResponseEntity(ex,apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, ApiError apiError) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
