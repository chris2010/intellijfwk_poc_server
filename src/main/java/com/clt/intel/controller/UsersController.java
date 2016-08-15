package com.clt.intel.controller;

import com.clt.intel.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liujinbang on 16/7/3.
 */


@Controller
@RequestMapping("user")
public class UsersController {


    /**
     * 根据ID查询user信息
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public User  get(@PathVariable("id") String id){

        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setAccount("123");
        user.setAccountname("GET张三!");
        return user;

    }

    /**
     * POST方法，FLAG为ID，body传user对象详情
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseBody
    public User  post(@PathVariable("id") String id){

        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setAccount("123");
        user.setAccountname("post创建用户!");

        return user;

    }

    /**
     * PUT 方法不需要ID，为了统一此处写默认ID，入参可为 0 ，ID为后台自动生成
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public User  put(@PathVariable("id") String id ){

        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setAccount("123");
        user.setAccountname("张三修改用户,PUT方法!");

        return user;

    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public User  delete(@PathVariable("id") String id ){

        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setAccount("123");
        user.setAccountname("张三用户,DELETE方法!");

        return user;

    }

}
