package com.leo.rbac.config.security;

import com.leo.rbac.config.security.filter.CheckTokenFilter;
import com.leo.rbac.config.security.handler.AnonymousAuthenticationHandler;
import com.leo.rbac.config.security.handler.CustomerAccessDeniedHandler;
import com.leo.rbac.config.security.handler.LoginFailureHandler;
import com.leo.rbac.config.security.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CheckTokenFilter checkTokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //表单登录
        http.formLogin()
                .loginProcessingUrl("/api/user/login")
                // 设置登录验证成功或失败后的的跳转地址
                .successHandler(loginSuccessHandler).failureHandler(loginFailureHandler) //成功和失败的处理
                // 禁用csrf防御机制
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() //设置拦截的请求
                .antMatchers("/api/user/login").permitAll() //登录请求不拦截
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)
                .accessDeniedHandler(customerAccessDeniedHandler)
                .and().cors();//开启跨域配置
    }

}
