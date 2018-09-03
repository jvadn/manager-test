package com.ch.manager.service;

import com.ch.manager.api.TestApi;
import com.ch.manager.dao.TestDao;
import com.ch.manager.entity.MtTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestApi {

    @Autowired
    TestDao dao;

    @Override
    public MtTest getNameAndAge(String id) {
        MtTest test = new MtTest();
        MtTest testEntity = dao.queryNameAndAgeById("1");
        if(testEntity != null){
            return testEntity;
        }
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
