package com.movie.highcirclemaster.config;

import com.movie.highcirclemaster.security.ApiKeyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilterRegistration(ApiKeyFilter apiKeyFilter) {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(apiKeyFilter);
        registrationBean.addUrlPatterns("/movies/*"); // Apply filter to specific endpoints
        registrationBean.setOrder(1); // Set filter order
        return registrationBean;
    }
}
