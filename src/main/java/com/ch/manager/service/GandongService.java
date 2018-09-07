package com.ch.manager.service;

import com.ch.manager.api.GandongApi;
import com.ch.manager.dao.GandongDao;
import com.ch.manager.entity.MtGandong;
import com.ch.manager.service.Base.BaseService;
import com.ch.manager.utils.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhao
 * @date ${date}
 */
@Service
public class GandongService extends BaseService implements GandongApi {

    @Autowired
    GandongDao dao;

    @Override
    public void add(MtGandong gandong) {
        dao.insertTemplate(gandong);
    }

    @Override
    public MtGandong queryByUserIdType(String userId, String type) {
        List<MtGandong> gandongs = dao.template(new MtGandong(userId, type));
        return CollectionUtil.isEmpty(gandongs) ? null : gandongs.get(0);
    }

    @Override
    public void removeByUserIdType(String userId, String type) {
        dao.removeByUserIdType(userId, type);
    }
}
