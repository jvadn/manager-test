package com.ch.manager;

import com.ch.manager.api.UserApi;
import com.ch.manager.entity.UserContext;
import com.ch.manager.filter.UserLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

    @Autowired
    UserApi userApi;

    @Autowired
    protected Environment env;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userApi.updateContext();
        System.out.println("------------------------>启动完成，配置为：" + env.getProperty("spring.profiles.active"));
    }
}
