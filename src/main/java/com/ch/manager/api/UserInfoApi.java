package com.ch.manager.api;

import com.ch.manager.entity.MtUserInfo;

public interface UserInfoApi {

    MtUserInfo queryByUserId(String userId);

}
