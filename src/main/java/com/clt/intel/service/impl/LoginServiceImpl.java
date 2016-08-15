package com.clt.intel.service.impl;

import com.clt.base.dto.ReturnParmDto;
import com.clt.intel.bean.System;
import com.clt.intel.bean.User;
import com.clt.intel.bean.UserSystem;
import com.clt.intel.dao.SystemMapper;
import com.clt.intel.dao.UserMapper;
import com.clt.intel.dao.UserSystemMapper;
import com.clt.intel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujinbang on 16/6/16.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSystemMapper userSystemMapper;
    @Autowired
    private SystemMapper systemMapper;


    public UserMapper getUserMapper() {
        return userMapper;
    }


    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUserByParm(User user) {


        return userMapper.getUserByParm(user);
    }

    @Transactional
    @Override
    public ReturnParmDto login(User user) {

        ReturnParmDto<User> returnParmDto = new ReturnParmDto<User>();

        User user1 = new User();
        user1.setAccount(user.getAccount());

        List<User> users = userMapper.getUserByParm(user1);
        if (users == null || users.size() == 0) { //账号错误,无此用户
            returnParmDto.setStatus(0);//失败
            returnParmDto.setMsg("账号错误!");

        } else {
            if (users.get(0).getPasw() != null &&
                    !users.get(0).getPasw().trim().isEmpty()
                    && users.get(0).getPasw().trim().equals(user.getPasw())
                    ) {
                returnParmDto.setStatus(1);//失败
                returnParmDto.setMsg("登录成功!");
                returnParmDto.setT(users.get(0));
            } else {
                returnParmDto.setStatus(0);//失败
                returnParmDto.setMsg("密码错误!");
            }
        }

        return returnParmDto;
    }

    @Override
    public ReturnParmDto regMobileUser(User user) {

        ReturnParmDto returnParmDto = new ReturnParmDto();

        /**
         * 校验当前登录名是否重复
         */

        User user1 = new User();
        user1.setAccount(user.getAccount());

        List<User> users = userMapper.getUserByParm(user1);

        if (users == null || users.isEmpty()) {

            int res = userMapper.insert(user);

            if (res == 1) {
                returnParmDto.setStatus(1);
                returnParmDto.setMsg("用户注册成功!");
            } else {
                returnParmDto.setStatus(0);
                returnParmDto.setMsg("用户注册失败!");
            }
        } else {
            returnParmDto.setStatus(0);
            returnParmDto.setMsg("此账号已被使用!");
        }

        return returnParmDto;
    }

    @Override
    public ReturnParmDto<List<Map>> mobileMarkList(Integer markType, Integer userId) {

        Map<String, Integer> stringIntegerMap = new HashMap<String, Integer>();

        stringIntegerMap.put("markType", markType);
        stringIntegerMap.put("userId", userId);

        List<Map> maps = userMapper.mobileMarkList(stringIntegerMap);

        ReturnParmDto<List<Map>> returnParmDto = new ReturnParmDto<List<Map>>();

        returnParmDto.setStatus(1);
        returnParmDto.setT(maps);
        return returnParmDto;
    }

    @Override
    public ReturnParmDto<String> mobileMark(Integer systemId, Integer userId) {

        ReturnParmDto<String> parmDto = new ReturnParmDto<String>();


        UserSystem userSystem = new UserSystem();

        userSystem.setSystemId(systemId.longValue());
        userSystem.setUserId(userId.longValue());


        try {

            userSystemMapper.insert(userSystem);
            parmDto.setStatus(1);
        } catch (Exception e) {
            e.printStackTrace();
            parmDto.setStatus(1);
            parmDto.setMsg(e.getMessage());
        }
        return parmDto;
    }


    /**
     * 后台用户登录服务
     *
     * @param system
     * @return
     */
    @Override
    public ReturnParmDto regManageUser(System system) {

        ReturnParmDto returnParmDto = new ReturnParmDto();

        /*
         * 判断邮箱是否重复
         */
        System system1 = new System();

        system1.setCompEmail(system.getCompEmail());

        List<System> systems = systemMapper.selectSystemByParm(system);

        if (systems == null || systems.size() == 0) {
            int res = systemMapper.insert(system);


            if (res == 1) {
                returnParmDto.setStatus(1);
                returnParmDto.setMsg("用户注册成功!");
            } else {
                returnParmDto.setStatus(0);
                returnParmDto.setMsg("用户注册失败!");
            }


        } else {
            returnParmDto.setStatus(0);
            returnParmDto.setMsg("此邮箱已被使用!");
        }


        return returnParmDto;
    }

    /**
     * 后台管理段用户登录
     *
     * @param system
     * @return
     */
    @Override
    public ReturnParmDto<System> loginManageUser(System system) {

        ReturnParmDto<System> returnParmDto = new ReturnParmDto<System>();


        System system1 = new System();

        system1.setCompEmail(system.getCompEmail());

        List<System> systems = systemMapper.selectSystemByParm(system1);

        if (systems == null || systems.size() == 0) {
            //int res = systemMapper.insert(system);

            returnParmDto.setStatus(0);
            returnParmDto.setMsg("当前邮箱账号不存在,请检测!");
        } else {
            if (system.getCompPasw().trim().equals(systems.get(0).getCompPasw().trim())){
                returnParmDto.setStatus(1);
                returnParmDto.setMsg("登录成功!");
                returnParmDto.setT(systems.get(0));
            }else{
                returnParmDto.setStatus(0);
                returnParmDto.setMsg("密码错误!");
            }


        }

        return returnParmDto;
    }

    @Override
    public ReturnParmDto<List<Map>> findMarkedUsers(System system) {

        System system1 = new System();

        system1.setSystemId( system.getSystemId());

        List<Map> maps = systemMapper.findMarkedUsers(system);


        ReturnParmDto<List<Map> > mapReturnParmDto = new ReturnParmDto<List<Map> >();

        mapReturnParmDto.setStatus(1);
        mapReturnParmDto.setT(maps);

        return mapReturnParmDto;
    }

    @Override
    public ReturnParmDto updateSystem(System system) {

        systemMapper.updateByPrimaryKey(system);

        return null;
    }
}
