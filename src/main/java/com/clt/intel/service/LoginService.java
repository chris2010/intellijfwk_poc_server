package com.clt.intel.service;

import com.clt.base.dto.ReturnParmDto;
import com.clt.intel.bean.System;
import com.clt.intel.bean.User;

import java.util.List;
import java.util.Map;

/**
 * Created by liujinbang on 16/6/16.
 */
public interface LoginService {


    List<User> getUserByParm(User user);

    ReturnParmDto login(User user);

    ReturnParmDto regMobileUser(User user);

    ReturnParmDto<List<Map>> mobileMarkList(Integer markType, Integer userId);

    ReturnParmDto<String> mobileMark(Integer systemId, Integer userId);

    ReturnParmDto regManageUser(System system);

    ReturnParmDto<System> loginManageUser(System system);

    ReturnParmDto<List<Map>> findMarkedUsers(System system);

    ReturnParmDto updateSystem(System system);
}
