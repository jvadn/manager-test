package com.ch.manager.dao;

import com.ch.manager.entity.MtUser;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<MtUser> {
}
