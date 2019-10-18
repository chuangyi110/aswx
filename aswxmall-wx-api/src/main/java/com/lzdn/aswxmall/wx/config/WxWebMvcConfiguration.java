package com.lzdn.aswxmall.wx.config;

import com.lzdn.aswxmall.wx.annotation.support.LoginUserHandlerMethodArgumentResolver;
import com.lzdn.aswxmall.wx.annotation.support.LoginUserLevelHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WxWebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
        argumentResolvers.add(new LoginUserLevelHandlerMethodArgumentResolver());
    }
}
