package com.ch.manager.api;

import com.ch.manager.entity.MtMenu;

import java.util.List;

public interface MenuApi {

    List<MtMenu> queryByUserId(String userId);

}
