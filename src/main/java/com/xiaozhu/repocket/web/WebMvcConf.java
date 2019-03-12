package com.xiaozhu.repocket.web;

import com.xiaozhu.repocket.interceptor.ProcessInterceptor;
import com.xiaozhu.repocket.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConf extends WebMvcConfigurerAdapter {

    @Autowired
    private UserInterceptor authInterceptor;



    @Autowired
    private ProcessInterceptor processInterceptor;

    /**
     * 请求拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(processInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").
                excludePathPatterns("/user/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
