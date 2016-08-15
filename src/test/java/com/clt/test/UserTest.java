/*package com.clt.test;

import com.clt.web.bean.Customer;
import com.clt.web.service.UserService;
import com.clt.web.utils.BeanUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

*//**
 * Created by liujinbang on 15/8/26.
 *//*
public class UserTest {
    private UserService userService;
    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext.xml"
                ,"classpath*:spring-mybatis.xml"});
        userService = (UserService) context.getBean("userService");
    }

    @Test
    public  void test () throws  Exception{
       // addUser();

        copyProtityTest();

    }


    public void addUser(){
        Customer user = new Customer();
        user.setNickname("你好");
        user.setState(2);
        System.out.println(userService.insert(user));
    }

    public  void copyProtityTest() throws  Exception{

        Customer user = new Customer();
        user.setNickname("你好");
        user.setState(2);
        user.setCreateEmp(123);
        Customer bean = (Customer) BeanUtils.copyProperties(user, Customer.class);

        System.out.println(bean.getNickname());
        System.out.println(bean.getCreateEmp());


    }


}
*/