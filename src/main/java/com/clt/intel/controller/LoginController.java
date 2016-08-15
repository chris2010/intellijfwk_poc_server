package com.clt.intel.controller;

import com.clt.base.controller.BaseController;
import com.clt.base.dto.ReturnParmDto;
import com.clt.framework.bean.PVLogBean;
import com.clt.intel.bean.User;
import com.clt.intel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by liujinbang on 16/6/15.
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {


    @Autowired
    public LoginService loginService;


    /**
     * 移动端用户登录
     *
     * @return
     */
    @RequestMapping(value = "/mobile_userLogin")
    @ResponseBody
    public ReturnParmDto<User> mobileLogin(User user, HttpServletResponse res) {
        ReturnParmDto<User> parmDto = new ReturnParmDto<User>();
        try {


            String account = user.getAccount();
            String pasw = user.getPasw();

            if (account == null || account.trim().equals("")) {
                parmDto.setStatus(RES_FAIL);
                parmDto.setMsg("账号不能为空!");
            } else if (pasw == null || pasw.trim().equals("")) {
                parmDto.setStatus(RES_FAIL);
                parmDto.setMsg("密码不能为空!");
            } else {
                /**
                 * 调用服务 校验用户名、密码的准确性
                 */


                ReturnParmDto<User> returnParmDto = loginService.login(user);

                if (returnParmDto.getStatus() == 0) {
                    parmDto.setStatus(RES_FAIL);
                    parmDto.setMsg(returnParmDto.getMsg());
                } else if (returnParmDto.getStatus() == 1) {
                    parmDto.setStatus(RES_SUCCESS);
                    parmDto.setMsg(returnParmDto.getMsg());
                    parmDto.setT(returnParmDto.getT());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return setHeaders(res, parmDto);
    }


    /**
     * 移动端用户注册
     *
     * @return
     */
    @RequestMapping("/mobile_userReg")
    @ResponseBody
    public ReturnParmDto<String> mobileUserReg(User user, HttpServletResponse res) {
        ReturnParmDto<String> parmDto = new ReturnParmDto<String>();

        String account = user.getAccount();
        String pasw = user.getPasw();
        String accoutName = user.getAccountname();//昵称

        if (account == null || account.trim().equals("")) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("账号 不能为空!");
        } else if (pasw == null || pasw.trim().equals("")) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("密码不能为空!");
        } else if (accoutName == null || accoutName.trim().equals("")) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("昵称不能为空!");
        } else {
            /**
             * 调用服务 注册移动端用户
             */
            ReturnParmDto returnParmDto = loginService.regMobileUser(user);

            if (returnParmDto.getStatus() == 0) {
                parmDto.setStatus(RES_FAIL);
                parmDto.setMsg(returnParmDto.getMsg());
            } else if (returnParmDto.getStatus() == 1) {
                parmDto.setStatus(RES_SUCCESS);
                parmDto.setMsg(returnParmDto.getMsg());
            }
        }

        return setHeaders(res, parmDto);
    }


    /**
     * 查询已关注、未关注列表
     *
     * @param markType 1 已关注 2 未关注
     *                 userId   用户ID
     * @return
     */
    @RequestMapping("/mobile_mobileMarkList")
    @ResponseBody
    public ReturnParmDto<List<Map>> mobileMarkList(Integer markType, Integer userId, HttpServletResponse res) {
        ReturnParmDto<List<Map>> parmDto = new ReturnParmDto<List<Map>>();

        if (markType == null) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("关注类型不能为空!");
        } else if (userId == null) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("用户ID不能为空!");
        } else {
            /**
             * 调用服务 注册移动端用户
             */
            ReturnParmDto<List<Map>> returnParmDto = loginService.mobileMarkList(markType, userId);

            if (returnParmDto.getStatus() == 0) {
                parmDto.setStatus(RES_FAIL);
                parmDto.setMsg(returnParmDto.getMsg());

            } else if (returnParmDto.getStatus() == 1) {
                parmDto.setStatus(RES_SUCCESS);
                parmDto.setMsg(returnParmDto.getMsg());
                parmDto.setT(returnParmDto.getT());
            }
        }

        return setHeaders(res, parmDto);
    }


    /**
     * 关注
     *
     * @param systemId 公司ID
     *                 userId   用户ID
     * @return
     */
    @RequestMapping("/mobile_mobileMark")
    @ResponseBody
    public ReturnParmDto<String> mobileMark(Integer systemId, Integer userId, HttpServletResponse res) {
        ReturnParmDto<String> parmDto = new ReturnParmDto<String>();


        if (systemId == null) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("系统ID不能为空!");
        } else if (userId == null) {
            parmDto.setStatus(RES_FAIL);
            parmDto.setMsg("用户ID不能为空!");
        } else {
            /**
             * 调用服务 关注
             */
            ReturnParmDto<String> returnParmDto = loginService.mobileMark(systemId, userId);

            if (returnParmDto.getStatus() == 0) {
                parmDto.setStatus(RES_FAIL);
                parmDto.setMsg(returnParmDto.getMsg());

            } else if (returnParmDto.getStatus() == 1) {
                parmDto.setStatus(RES_SUCCESS);
                parmDto.setMsg(returnParmDto.getMsg());
                parmDto.setT(returnParmDto.getT());
            }
        }

        return setHeaders(res, parmDto);
    }

}
