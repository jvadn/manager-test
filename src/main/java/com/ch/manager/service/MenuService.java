package com.ch.manager.service;

import com.ch.manager.api.MenuApi;
import com.ch.manager.dao.MenuDao;
import com.ch.manager.entity.MtMenu;
import com.ch.manager.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService extends BaseService implements MenuApi {

    @Autowired
    MenuDao dao;

    @Override
    public List<MtMenu> queryByUserId(String userId) {
        return dao.template(new MtMenu(userId));
    }
}
