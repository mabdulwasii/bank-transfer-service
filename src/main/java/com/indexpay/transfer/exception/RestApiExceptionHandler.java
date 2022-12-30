package com.indexpay.transfer.exception;

import com.indexpay.transfer.service.constant.ResponseCode;
import com.indexpay.transfer.service.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(" handleHttpMessageNotReadable ", ex);
        String errorMessage = "Invalid request payload";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    // Handle handleClientErrorException. Happens when request Entity is unprocessable.
    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleClientErrorException(HttpClientErrorException ex) {
        log.error(" handleClientErrorException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(), String.valueOf(ex.getLocalizedMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Handles IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(" handleIllegalArgumentException ", ex);
        String errorMessage = ex.getLocalizedMessage();
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    // Handles ResourceAccessException
    @ExceptionHandler(ResourceAccessException.class)
    protected ResponseEntity<Object> handleResourceAccessException(ResourceAccessException ex) {
        log.error(" handleResourceAccessException exception ", ex);
        String errorMessage = "Invalid access to resource";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.UNAUTHORIZED);
    }

    // Handles ConnectException
    // Handle ConnectException. Happens when request JSON is malformed.
    @ExceptionHandler(ConnectException.class)
    protected ResponseEntity<Object> handleConnectException(ConnectException ex) {
        log.error(" handleConnectException ", ex);
        String errorMessage = "Remote connection not found";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    // Handles AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        log.error(" handleAccessDeniedException ", ex);
        String errorMessage = "Access denied";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.FORBIDDEN);
    }

    // Handles IllegalStateException
    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<Object> handleIllegalException(IllegalStateException ex, WebRequest request) {
        log.error(" handleIllegalException ", ex);
        String errorMessage = "Malformed JSON request";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    // Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(Exception ex, WebRequest request) {
        log.error(" handleEntityNotFound ", ex);
        String errorMessage = "Resource object not found";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.NOT_FOUND);
    }

    //Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.error(" handleMissingServletRequestParameter ", ex);
        String errorMessage = "\"" + ex.getParameterName() + "\" parameter is missing";
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    //  Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid.
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status,
                                                                     WebRequest request) {
        log.error(" handleHttpMediaTypeNotSupported ", ex);

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        return buildResponseEntity(ResponseCode.ERROR.getCode(), builder.substring(0, builder.length() - 2), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(" handleMethodArgumentNotValid ", ex);
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String errorMessage = objectError.getDefaultMessage();
            errors.add(errorMessage);
        });

        return buildResponseEntity(ResponseCode.ERROR.getCode(), String.join("\n", errors).replace(",", ""),
                HttpStatus.BAD_REQUEST);
    }

    //Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        log.error(" handleConstraintViolation ", ex);
        Map<String, String> errors = new HashMap<>();

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        constraintViolations.forEach(constraintViolation -> {
            String message = constraintViolation.getMessage();
            String messageTemplate = constraintViolation.getMessageTemplate();
            errors.put(messageTemplate, message);
        });

        return buildResponseEntity(
                ResponseCode.ERROR.getCode(),
                errors.entrySet()
                        .stream()
                        .map(e -> e.getKey() + " : " + e.getValue())
                        .collect(Collectors.joining("\n")),
                HttpStatus.BAD_REQUEST);
    }

    // Handle HttpMessageNotWritableException.
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.error(" handleHttpMessageNotWritable ", ex);
        String errorMessage = "Error writing JSON output";
        logger.error("Error writing JSON output ==> Cause " + ex.getCause());
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage, HttpStatus.BAD_REQUEST);
    }

    //Handle NoHandlerFoundException.
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        log.error(" handleHttpMessageNotWritable ", ex);
        String errorMessage = String.format("Could not find the %s method for URL %s",
                ex.getHttpMethod(), ex.getRequestURL());
        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    //Handle MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error("handleMethodArgumentTypeMismatch ", ex);
        String errorMessage = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType());

        return buildResponseEntity(ResponseCode.ERROR.getCode(), errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    //Handle DataIntegrityViolationException, inspects the cause for different DB causes.
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(
            DataIntegrityViolationException ex, WebRequest request) {
        log.error("handleDataIntegrityViolation ", ex);
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(ResponseCode.ERROR.getCode(), "Database error",
                    HttpStatus.BAD_REQUEST);
        }

        return buildResponseEntity(ResponseCode.ERROR.getCode(), "Invalid input data",
                HttpStatus.BAD_REQUEST);
    }

    //Handle general exception
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        log.error("handleAllException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "An error occurred, please try again",
                HttpStatus.BAD_REQUEST
        );
    }

    //Handle throwable error
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleAllException(Throwable ex, WebRequest request) {
        log.error("handleAllThrowableException ", ex);

        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Error!, please try again",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<Object> handleHttpServerErrorException(
            HttpServerErrorException ex, WebRequest request) {
        log.error("handleHttpServerErrorException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Connection error, please try again",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {
        log.error("handleBadCredentialsException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Bad credentials", HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<Object> handleAInsufficientAuthenticationException(
            InsufficientAuthenticationException ex, WebRequest request) {
        log.error("handleAInsufficientAuthenticationException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Insufficient authentication",
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        log.error("handleNullPointerException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "A null error occurred! Please try again",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Object> handleNumberFormatException(
            NumberFormatException ex, WebRequest request) {
        log.error("handleNumberFormatException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Invalid Number format",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(GenericException.class)
    protected ResponseEntity<Object> handleGenericException(GenericException ex, WebRequest request) {
        log.error("handleGenericException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                ex.getMessage(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NonUniqueReferenceException.class)
    protected ResponseEntity<Object> handleNonUniqueReferenceException(NonUniqueReferenceException ex, WebRequest request) {
        log.error("handleNonUniqueReferenceException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                ex.getMessage(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidEnumException.class)
    protected ResponseEntity<Object> handleInvalidEnumException(
            InvalidEnumException ex, WebRequest request) {
        log.error("handleInvalidEnumException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                ex.getMessage(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidTransactionReference.class)
    protected ResponseEntity<Object> handleInvalidTransactionReference(
            InvalidTransactionReference ex, WebRequest request) {
        log.error("handleInvalidTransactionReference ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                ex.getMessage(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NonUniqueResultException.class)
    protected ResponseEntity<Object> handleNonUniqueResultException(
            NonUniqueResultException ex, WebRequest request) {
        log.error("handleNonUniqueResultException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Data already exist", HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SocketTimeoutException.class)
    protected ResponseEntity<Object> handleSocketTimeoutException(
            SocketTimeoutException ex, WebRequest request) {
        log.error("handleNonUniqueResultException ", ex);
        return buildResponseEntity(ResponseCode.ERROR.getCode(),
                "Api timeout", HttpStatus.REQUEST_TIMEOUT
        );
    }

    private ResponseEntity<Object> buildResponseEntity(String code, String errorMessage,
                                                       HttpStatus status) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .code(code)
                .description(errorMessage)
                .build();
        return new ResponseEntity<>(response, status);
    }
}