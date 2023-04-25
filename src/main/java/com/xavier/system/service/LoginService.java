package com.xavier.system.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-25  16:55
 * @Description: TODO
 * @Version: 1.0
 */
public interface LoginService {

    public String determineErrorType(HttpServletRequest request);

}
