package com.xavier.security.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-25  14:31
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class CustomerLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onLogoutSuccess(request, response, authentication);
        logger.info(String.format("IP %s，用户 %s， 于 %s 退出系统。"
                , request.getRemoteHost(), authentication.getName(), LocalDateTime.now()));
    }
}
