package com.ch.manager.service;

import com.ch.manager.api.TestApi;
import com.ch.manager.entity.TestEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestApi {

    @Override
    public TestEntity getNameAndAge(String id) {
        TestEntity test = new TestEntity();
        if (id.equals("0")) {
            test.setName("陈豪");
            test.setAge("24");
        } else if (id.equals("1")) {
            test.setName("张娜");
            test.setAge("24");
        }
        return test;
    }
}
