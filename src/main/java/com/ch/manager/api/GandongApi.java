package com.ch.manager.api;

import com.ch.manager.entity.MtGandong;

/**
 * @author chenhao
 * @date ${date}
 */
public interface GandongApi {

    void add(MtGandong gandong);

    MtGandong queryByUserIdType(String userId, String type);

    void removeByUserIdType(String userId, String type);

}
