package com.clt.intel.controller;

import com.clt.base.controller.BaseController;
import com.clt.base.dto.ReturnParmDto;
import com.clt.intel.bean.System;
import com.clt.intel.service.LoginService;
import org.apache.log4j.net.SyslogAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 后台管理端相关controller
 * <p>
 * Created by liujinbang on 16/6/17.
 */
@Controller
@RequestMapping("c_manageController")
public class ManageController extends BaseController {


    @Autowired
    public LoginService loginService;

    /**
     * 企业端注册服务
     *
     * @param system
     * @return
     */
    @RequestMapping("cManageReg")
    @ResponseBody
    public ReturnParmDto cManageReg(System system, HttpServletResponse res, HttpServletRequest request) {

        ReturnParmDto returnParmDto = new ReturnParmDto();
        if (system != null) {

            if (system.getCompName() == null || system.getCompName().trim().isEmpty()) {
                returnParmDto.setStatus(RES_FAIL); //compLegalPerson
                returnParmDto.setMsg("输入参数公司名称为空!");
            } else if (system.getCompLegalPerson() == null || system.getCompLegalPerson().trim().isEmpty()) {
                returnParmDto.setStatus(RES_FAIL); //compLegalPerson
                returnParmDto.setMsg("输入参数企业法人为空!");
            } else if (system.getCompPasw() == null || system.getCompPasw().trim().isEmpty()) {
                returnParmDto.setStatus(RES_FAIL); //compLegalPerson
                returnParmDto.setMsg("输入参数企业登录密码为空!");
            } else if (system.getCompEmail() == null || system.getCompEmail().trim().isEmpty()) {
                returnParmDto.setStatus(RES_FAIL); //compLegalPerson
                returnParmDto.setMsg("输入参数登录邮箱为空!");
            } else {

                ReturnParmDto returnParmDto1 = loginService.regManageUser(system);

                if (returnParmDto1.getStatus() == 0) {
                    returnParmDto.setStatus(RES_FAIL);
                } else {
                    returnParmDto.setStatus(RES_SUCCESS);
                }
                returnParmDto.setMsg(returnParmDto1.getMsg());
            }
        } else {
            returnParmDto.setStatus(RES_FAIL);
            returnParmDto.setMsg("输入参数为空!");
        }


        return returnParmDto;

    }


    /**
     * 后台企业用户d登录
     *
     * @param system
     * @return
     */
    @RequestMapping("cManageLogin")
    @ResponseBody
    public ReturnParmDto cManageLogin(System system, HttpServletRequest request, HttpServletResponse response) {
        ReturnParmDto returnParmDto = new ReturnParmDto();
        if (system != null) {
            if (system.getCompPasw() == null || system.getCompPasw().trim().isEmpty()) {
                returnParmDto.setStatus(RES_FAIL); //compLegalPerson
                returnParmDto.setMsg("输入参数企业登录密码为空!");
            } else if (system.getCompEmail() == null || system.getCompEmail().trim().isEmpty()) {
                returnParmDto.setStatus(RES_FAIL); //compLegalPerson
                returnParmDto.setMsg("输入参数登录邮箱为空!");
            } else {

                ReturnParmDto<System> returnParmDto1 = loginService.loginManageUser(system);

                if (returnParmDto1.getStatus() == 0) {
                    returnParmDto.setStatus(RES_FAIL);
                } else {
                    returnParmDto.setStatus(RES_SUCCESS);
                    /**
                     * 将当前用户信息放置在session中
                     */
                    request.getSession().setAttribute("userSession", returnParmDto1.getT());
                }
                returnParmDto.setMsg(returnParmDto1.getMsg());
            }

        } else {
            returnParmDto.setStatus(RES_FAIL);
            returnParmDto.setMsg("输入参数为空!");
        }
        return setHeaders(response, returnParmDto);
    }

    /**
     * 后台企业已关注用户列表
     *
     * @return
     */
    @RequestMapping("cGetMarkUsers")
    @ResponseBody
    public ReturnParmDto cManageGetMarkUsers(HttpServletRequest request, HttpServletResponse response) {
        ReturnParmDto returnParmDto = new ReturnParmDto();
        Object system_obj = request.getSession().getAttribute("userSession");

        if (system_obj != null) {

            System system = (System) system_obj;
            ReturnParmDto<List<Map>> parmDto = loginService.findMarkedUsers(system);

            returnParmDto.setStatus(RES_SUCCESS);
            returnParmDto.setT(parmDto.getT());
        } else {
            returnParmDto.setStatus(0);
            returnParmDto.setMsg("登录信息为空,请退出重新登录!");
        }

        return  returnParmDto;
    }

    /**
     * 企业后台修改配置信息
     *
     * @return
     */
    @RequestMapping("modifyCompInfo")
    public String modifyCompInfo(HttpServletRequest request, HttpServletResponse response) {

        ReturnParmDto returnParmDto = new ReturnParmDto();

        if (request instanceof MultipartHttpServletRequest) {

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            MultipartFile file = multipartRequest.getFiles("sys_url_pic").get(0);
            long size = file.getSize();
            byte[] data = new byte[(int) size];
            InputStream input = null;
            try {
                input = file.getInputStream();
                input.read(data);
                request.getSession().getServletContext().getRealPath("");

                /*8
                    根据温建
                 */
                String fileName = UUID.randomUUID().toString()+"." +file.getOriginalFilename().split("\\.")
                        [file.getOriginalFilename().split("\\.").length - 1];

                File outFile = new File(request.getSession().getServletContext().getRealPath("")
                        + File.separator
                        + "files"
                        + File.separator
                         +
                        fileName);

                if (!outFile.getParentFile().exists()) {
                    outFile.getParentFile().mkdirs();
                }

                if (!outFile.exists()) {
                    outFile.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(outFile);

                outStream.write(data);
                outStream.close();
                input.close();

                /*文件保存完毕*/

                /*
                  组织其他参数
                 */
                Object system_obj = request.getSession().getAttribute("userSession");

                if (system_obj != null) {

                    System system = (System) system_obj;



                    system.setSysPic(fileName);
                    system.setSysTitle(request.getParameter("sysTitle"));
                    system.setSysUrl(request.getParameter("sysUrl"));

                    system.setAppSec(UUID.randomUUID().toString());
                    system.setAppToken(UUID.randomUUID().toString());
                    system.setAppid(UUID.randomUUID().toString());

                    returnParmDto = loginService.updateSystem(system);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return "/index";
    }


    /**
     * 文件下载
     * @param fileName
     * @param response
     */
    @RequestMapping("/download")
    public void downloadFile(String fileName,HttpServletResponse response,HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");

        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
        try {
            File file=new File(fileName);
           // System.out.println(file.getAbsolutePath());
            InputStream inputStream=new FileInputStream(request.getSession().getServletContext().getRealPath("")
                    + File.separator
                    + "files"
                    + File.separator
                    +
                    fileName);
            OutputStream os=response.getOutputStream();
            byte[] b=new byte[1024];
            int length;
            while((length=inputStream.read(b))>0){
                os.write(b,0,length);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
