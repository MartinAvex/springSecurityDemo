package com.xavier.system.service.impl;

import com.xavier.security.enumeration.LoginError;
import com.xavier.system.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-25  16:55
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String determineErrorType(HttpServletRequest request) {
        String error = request.getParameter("error");
        return LoginError.analysis(error).getMessage();
    }

}
