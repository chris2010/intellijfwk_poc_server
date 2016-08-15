package com.clt.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.clt.base.dto.ReturnParmDto;
import net.sf.json.JSONObject;

import com.clt.framework.romote.Executor.HttpExecutors.HttpExecute;
import com.clt.framework.romote.Executor.HttpExecutors.HttpRespons;
import com.clt.framework.utils.PropertiesReader;
//import com.clt.web.common.ConfigProperties;
//import com.clt.web.utils.AESToErp;
//import com.clt.web.utils.HttpClientUtil;
import com.clt.weixin.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by liujinbang on 15/8/26.
 * <p/>
 * 基础的controller类
 */
public class BaseController {

    public static Integer RES_SUCCESS = 1;//请求成功

    public static Integer RES_FAIL = 2; //请求失败

    public static Integer RES_WAITING = 3; //等待

    public static String msg;  //返回提示信息



    public static ReturnParmDto setHeaders(HttpServletResponse response, ReturnParmDto dto) {
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Access-Control-Allow-Origin","*");
        return dto;
    }

}
