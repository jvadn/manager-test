package com.ch.manager.dao;

import com.ch.manager.entity.MtRemark;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarkDao extends BaseMapper<MtRemark> {
}
