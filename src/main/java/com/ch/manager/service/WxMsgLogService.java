package com.ch.manager.service;

import com.ch.manager.api.WxMsgLogApi;
import com.ch.manager.dao.WxMsgLogDao;
import com.ch.manager.entity.MtWxMsgLog;
import com.ch.manager.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxMsgLogService extends BaseService implements WxMsgLogApi {

    @Autowired
    private WxMsgLogDao dao;

    @Override
    public void addLog(MtWxMsgLog log) {
        dao.insert(log);
    }

}
