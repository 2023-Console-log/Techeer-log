package com.techeerlog.global.config;

import com.techeerlog.global.support.AuthInterceptor;
import com.techeerlog.global.support.token.AuthenticationPrincipalArgumentResolver;
import com.techeerlog.global.support.token.TokenManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Profile({"dev","local"})
public class WebMvcDevConfig implements WebMvcConfigurer {

    private static final String ALLOW_METHOD_NAMES = "GET,HEAD,POST,DELETE,TRACE,OPTIONS,PATCH,PUT";

    private final AuthInterceptor authInterceptor;
    private final TokenManager tokenManager;

    public WebMvcDevConfig(AuthInterceptor authInterceptor, TokenManager tokenManager) {
        this.authInterceptor = authInterceptor;
        this.tokenManager = tokenManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/auth/anonymous")
                .excludePathPatterns("/api/dummy")
                .excludePathPatterns("/api/v1/enums")
                .excludePathPatterns("/api/v1/health")
                .excludePathPatterns("/swagger-ui.html", "/swagger-ui/**", "/api/v3/api-docs/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController.class, Controller.class));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticationPrincipalArgumentResolver());
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver(tokenManager);
    }
}