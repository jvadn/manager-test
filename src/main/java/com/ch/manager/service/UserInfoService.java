package com.ch.manager.service;

import com.ch.manager.api.UserInfoApi;
import com.ch.manager.dao.UserInfoDao;
import com.ch.manager.entity.MtUserInfo;
import com.ch.manager.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends BaseService implements UserInfoApi {

    @Autowired
    UserInfoDao dao;

    @Override
    public MtUserInfo queryByUserId(String userId) {
        return dao.templateOne(new MtUserInfo(userId));
    }
}
