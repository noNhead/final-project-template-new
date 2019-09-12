package com.epam.rd.izh.configuration;

import com.epam.rd.izh.interceptor.AuthInterceptor;
import com.epam.rd.izh.interceptor.UserNameAwareInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигуратор интерсептеров. Настраивает защищаемые разделы.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Бин общего интерсептера проверки наличия аутентификации
     */
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public UserNameAwareInterceptor userNameAwareInterceptor() {
        return new UserNameAwareInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/login", "/registration",
                "/logout", "/css/**", "/js/**", "/checkloginexist");

        registry.addInterceptor(userNameAwareInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/login",
                "/registration", "/logout", "/css/**", "/js/**", "/checkloginexist");
    }

}
