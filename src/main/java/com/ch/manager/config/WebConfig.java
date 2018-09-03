package com.ch.manager.config;


import java.util.ArrayList;
import java.util.List;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 *
 * <p>
 * web配置类
 * </p>
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);

        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/templates/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

    /**
     * 权限验证
     * @return
     */
//    @Bean
    public FilterRegistrationBean permFilterRegistrationPermBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setName("ermissionValidationFilter");
//        PermissionValidationFilter ermissionValidationFilter=new PermissionValidationFilter();
//        registrationBean.setFilter(ermissionValidationFilter);
//        registrationBean.setOrder(1);
//        List<String> urlList = new ArrayList<String>();
//        urlList.add("/*");
//        registrationBean.setUrlPatterns(urlList);
//        registrationBean.addInitParameter("excludedPages", permExcludedPages);
        return registrationBean;
    }

}