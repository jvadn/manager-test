package com.ch.manager.dao;

import com.ch.manager.entity.MtMenu;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends BaseMapper<MtMenu> {
}
