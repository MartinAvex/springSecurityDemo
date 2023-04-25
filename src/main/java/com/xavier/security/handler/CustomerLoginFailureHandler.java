package com.xavier.security.handler;

import com.xavier.security.enumeration.LoginError;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-25  16:10
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class CustomerLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final CustomerLoginFailureHandler INSTANCE = new CustomerLoginFailureHandler();

    private static final String DEFAULT_FAILURE_URL = "/login_fail";

    private String defaultFailureUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //setDefaultFailureUrl(determineFailureUrl(exception));
        super.onAuthenticationFailure(request, response, exception);
        this.logger.info(String.format("IP %s 于 %s 尝试登录系统失败，失败原因：%s", request.getRemoteHost(), LocalDateTime.now(), exception.getMessage()));
    }

    private String determineFailureUrl(AuthenticationException exception) {
        //设置默认的登录错误页面
        defaultFailureUrl = StringUtils.hasLength(defaultFailureUrl) ? defaultFailureUrl : DEFAULT_FAILURE_URL;
        Integer errorCode = determineFailureType(exception).getCode();
        /*
        * Optional.ofNullable(errorCode)
                .map(code -> defaultFailureUrl.concat(defaultFailureUrl.indexOf("?") > 0 ? "&" : "?").concat("error=").concat(String.valueOf(errorCode)))
                .orElse(defaultFailureUrl);
        * */
        return defaultFailureUrl;
    }

    private LoginError determineFailureType(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return LoginError.BAD_CREDENTIALS;
        }
        if (exception instanceof LockedException) {
            return LoginError.LOCKED;
        }
        if (exception instanceof AccountExpiredException) {
            return LoginError.ACCOUNT_EXPIRED;
        }
        if (exception instanceof UsernameNotFoundException) {
            return LoginError.USERNAME_NOT_FOUND;
        }
        return LoginError.FAILURE;
    }

    public static CustomerLoginFailureHandler toInstance() {
        return INSTANCE != null ? INSTANCE : new CustomerLoginFailureHandler();
    }

    @Override
    public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }

}
