package com.zup.mercado.config.validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.zup.proposta.config.validator.FieldErrors;
import com.zup.proposta.config.validator.StandardError;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<FieldErrors> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldErrors> listFieldErrors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(errors -> {
            String messageContext = messageSource.getMessage(errors, LocaleContextHolder.getLocale());
            FieldErrors error = new FieldErrors(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.toString(), exception.getClass().toString(), errors.getField(), messageContext);
            listFieldErrors.add(error);
        });
        return listFieldErrors;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public StandardError handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        return new StandardError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                exception.getLocalizedMessage());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public StandardError handleFeignException(FeignException exception) {
        return new StandardError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                exception.getLocalizedMessage());
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public StandardError handleCustomNotFoundException(CustomNotFoundException exception) {
        return new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                exception.getMsg());
    }

    @ExceptionHandler(CustomBusinessRuleViolation.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public StandardError handleCustomBusinessRuleViolation(CustomBusinessRuleViolation exception) {
        return new StandardError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                exception.getMsg());
    }

}
