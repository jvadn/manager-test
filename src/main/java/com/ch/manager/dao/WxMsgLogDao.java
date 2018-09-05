package com.ch.manager.dao;

import com.ch.manager.entity.MtWxMsgLog;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface WxMsgLogDao extends BaseMapper<MtWxMsgLog> {
}
