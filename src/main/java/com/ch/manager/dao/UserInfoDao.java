package com.ch.manager.dao;

import com.ch.manager.entity.MtUserInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends BaseMapper<MtUserInfo> {

    void removeByUserId(@Param("userId") String userId);

}
