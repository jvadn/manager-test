package com.ch.manager.api;

import com.ch.manager.entity.MtUserInfo;

public interface UserInfoApi {

    MtUserInfo queryByUserId(String userId);

    void addUserInfo(MtUserInfo userInfo);

    void removeByUserId(String userId);

}
