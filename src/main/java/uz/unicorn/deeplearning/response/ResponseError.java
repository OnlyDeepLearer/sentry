package uz.unicorn.deeplearning.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:21 PM
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseError {
    private String message;
    private String path;
    private String reason;

    public ResponseError(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public ResponseError(String reason, String message, @Nullable HttpServletRequest request) {
        this(reason, message);
        if (Objects.nonNull(request)) this.path = request.getRequestURI();
    }

    public static ResponseError response(String reason, String message) {
        return new ResponseError(reason, message);
    }

    public static ResponseError response(String reason, String message, @Nullable HttpServletRequest request) {
        return new ResponseError(reason, message, request);
    }

}
