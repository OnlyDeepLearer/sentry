package uz.unicorn.deeplearning.configs;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import uz.unicorn.deeplearning.user.User;
import uz.unicorn.deeplearning.user.UserService;
import uz.unicorn.deeplearning.utils.ThreadLocalSingleton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Doston Bokhodirov on 05 March 2023 at 10:43 PM
 */

@Component
@RequiredArgsConstructor
public class HandlerInterceptorConfiguration implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = getTokenFromRequest(request);
        User user = (User) userService.loadUserByUsername(token);
        ThreadLocalSingleton.setUser(user);
        return true;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return StringUtils.defaultIfEmpty(request.getHeader("token"), "");
    }

}
