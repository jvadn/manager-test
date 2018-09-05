package com.ch.manager;

import com.ch.manager.api.UserApi;
import com.ch.manager.entity.UserContext;
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
        System.out.println("------------------------>启动完成，配置为：" + env.getProperty("spring.profiles.active"));
        UserContext.setChenhao(userApi.queryByRemark("chenhao"));
        UserContext.setZhangna(userApi.queryByRemark("zhangna"));
        if (UserContext.getChenhao() != null) {
            System.out.println("陈豪信息加载完成。。。");
        }
        if (UserContext.getZhangna() != null) {
            System.out.println("张娜信息加载完成。。。");
        }
    }
}
