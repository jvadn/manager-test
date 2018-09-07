package com.ch.manager.dao;

import com.ch.manager.entity.MtGandong;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author chenhao
 * @date ${date}
 */
@Repository
public interface GandongDao extends BaseMapper<MtGandong>{

    void removeByUserIdType(@Param("userId") String userId, @Param("type") String type);

}
