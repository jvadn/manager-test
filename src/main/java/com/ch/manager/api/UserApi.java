package com.ch.manager.api;

import com.ch.manager.entity.MtUser;

public interface UserApi {

    MtUser queryByOpenId(String openId);

    MtUser queryByWxName(String wxName);

    MtUser queryByRemark(String remark);

    MtUser insert(MtUser mtUser);

    MtUser update(MtUser mtUser);

}
