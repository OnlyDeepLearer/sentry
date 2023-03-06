package uz.unicorn.deeplearning.exceptions;

import io.sentry.Sentry;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.unicorn.deeplearning.response.ResponseEntity;
import uz.unicorn.deeplearning.response.ResponseError;
import uz.unicorn.deeplearning.user.User;
import uz.unicorn.deeplearning.utils.ThreadLocalSingleton;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Doston Bokhodirov on 05 March 2023 at 10:53 PM
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex, HttpServletRequest request) {
        sentryCaptureException(ex, request);
        return getResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(final BadRequestException ex, HttpServletRequest request) {
        return getResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(final ConstraintViolationException ex, HttpServletRequest request) {
        sentryCaptureException(ex, request);
        return getResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(final Exception ex, HttpServletRequest request) {
        sentryCaptureException(ex, request);
        return getResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();
        return new org.springframework.http.ResponseEntity<>(
                ResponseEntity.ok(
                        ResponseError.response(
                                status.getReasonPhrase(),
                                String.join(", ", errorMessages)
                        )
                ),
                status
        );
    }

    private <E extends Exception> ResponseEntity<Object> getResponseEntity(E ex, HttpServletRequest request, HttpStatus status) {
        return ResponseEntity.error(ResponseError.response(status.getReasonPhrase(), ex.getMessage(), request), status);
    }

    private void sentryCaptureException(final Exception e, HttpServletRequest request) {
        Sentry.setUser(getSentryUser(request, ThreadLocalSingleton.getUser()));
        Sentry.setTag("Unhandled", "true");
        Sentry.captureException(e);
    }

    private io.sentry.protocol.User getSentryUser(HttpServletRequest request, User user) {
        io.sentry.protocol.User sentryUser = new io.sentry.protocol.User();
        sentryUser.setId(String.valueOf(user.getId()));
        sentryUser.setUsername(user.getPhone());
        sentryUser.setData(getUserData(request, user));
        return sentryUser;
    }

    private Map<String, String> getUserData(HttpServletRequest request, User user) {
        Map<String, String> userData = new HashMap<>();
        userData.put("URL", request.getRequestURI());
        userData.put("Name", StringUtils.defaultIfEmpty(user.getName(), "EMPTY"));
        return userData;
    }

}
