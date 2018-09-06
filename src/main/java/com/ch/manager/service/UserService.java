package com.ch.manager.service;

import com.ch.manager.api.UserApi;
import com.ch.manager.dao.UserDao;
import com.ch.manager.entity.MtUser;
import com.ch.manager.entity.UserContext;
import com.ch.manager.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService implements UserApi {

    @Autowired
    UserDao dao;

    @Override
    public MtUser queryByOpenId(String openId) {
        return dao.templateOne(new MtUser(openId));
    }

    @Override
    public MtUser queryByWxName(String wxName) {
        MtUser user = new MtUser();
        user.setWxName(wxName);
        return dao.templateOne(user);
    }

    @Override
    public MtUser queryByRemark(String remark) {
        MtUser user = new MtUser();
        user.setRemark(remark);
        return dao.templateOne(user);
    }

    @Override
    public MtUser insert(MtUser mtUser) {
        dao.insertTemplate(mtUser);
        return mtUser;
    }

    @Override
    public MtUser update(MtUser mtUser) {
        dao.updateTemplateById(mtUser);
        return mtUser;
    }

    @Override
    public void updateContext() {
        UserContext.setChenhao(queryByRemark("chenhao"));
        UserContext.setZhangna(queryByRemark("zhangna"));
    }
}
