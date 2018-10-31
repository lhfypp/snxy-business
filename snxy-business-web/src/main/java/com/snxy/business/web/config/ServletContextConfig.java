package com.snxy.business.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zcb on 2017/5/16.
 */
@Configuration
@Slf4j
public class ServletContextConfig extends WebMvcConfigurationSupport {


    @Autowired
    private  SystemUserInterceptor systemUserInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("配置拦截器映射");
        //  registry.addResourceHandler("/").addResourceLocations("/**");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    //    @Bean
//    public LoginInterceptor getLoginInterceptor() {
//        return new LoginInterceptor();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "business")
//    public SystemUserInterceptor getSystemUserInterceptor() {
//
//        return new SystemUserInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("配置拦截器");
//        SystemUserInterceptor systemUserInterceptor = getSystemUserInterceptor();
        registry.addInterceptor( systemUserInterceptor)//getSystemUserInterceptor())
                .addPathPatterns("/**");


    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    /**
     * 通过额外扩展默认的转换器，此处移除默认的StringHttpMessageConverter
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        Iterator<HttpMessageConverter<?>> converterIterator = converters.iterator();
        while (converterIterator.hasNext()) {
            HttpMessageConverter<?> cur = converterIterator.next();
            if (cur instanceof StringHttpMessageConverter) {
                converterIterator.remove();
                log.debug("移除默认的StringHttpMessageConverter");
                break;
            }
        }

        converters.add(0, responseBodyConverter());
    }

    // 解决返回乱码问题
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
}
