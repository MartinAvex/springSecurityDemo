package com.xavier.security.config;

import com.xavier.security.handler.CustomerAuthenticationSuccessHandler;
import com.xavier.security.handler.CustomerLoginFailureHandler;
import com.xavier.security.handler.CustomerLogoutSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author: Xavier.wu
 * @CreateTime: 2023-04-24  15:41
 * @Description: TODO
 * @Version: 1.0
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .successHandler(customerAuthenticationSuccessHandler())
                .failureHandler(customerLoginFailureHandler())
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/logout_success", "/login_fail").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(customerLogoutSuccessHandler())
                .permitAll();
    }

    /*放行静态资源*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/plugins/**", "/images/**", "/fonts/**");
    }

    /*登录成功处理器*/
    public AuthenticationSuccessHandler customerAuthenticationSuccessHandler() {
        CustomerAuthenticationSuccessHandler successHandler = new CustomerAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/index");
        return successHandler;
    }

    /*退出登录成功处理器*/
    public LogoutSuccessHandler customerLogoutSuccessHandler() {
        CustomerLogoutSuccessHandler successHandler = new CustomerLogoutSuccessHandler();
        successHandler.setDefaultTargetUrl("/logout_success");
        return successHandler;
    }

    /*登录失败处理器*/
    public AuthenticationFailureHandler customerLoginFailureHandler() {
        CustomerLoginFailureHandler failureHandler = new CustomerLoginFailureHandler();
        failureHandler.setDefaultFailureUrl("/login_fail");
        return failureHandler;
    }

}
