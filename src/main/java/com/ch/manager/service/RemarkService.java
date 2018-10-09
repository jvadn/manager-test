package com.ch.manager.service;

import com.ch.manager.api.RemarkApi;
import com.ch.manager.dao.RemarkDao;
import com.ch.manager.entity.MtRemark;
import com.ch.manager.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemarkService extends BaseService implements RemarkApi {

    @Autowired
    RemarkDao dao;

    @Override
    public void add(MtRemark remark) {
        remark.init();
        dao.insertTemplate(remark);
    }
}
