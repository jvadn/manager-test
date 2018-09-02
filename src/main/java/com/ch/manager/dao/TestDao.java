package com.ch.manager.dao;

import com.ch.manager.entity.MtTest;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao extends BaseMapper<MtTest> {

    MtTest queryNameAndAgeById(@Param("id") String id);

}
