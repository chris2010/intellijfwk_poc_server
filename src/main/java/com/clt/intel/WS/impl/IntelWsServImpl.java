package com.clt.intel.WS.impl;

import com.clt.intel.WS.IntelWsServ;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by liujinbang on 16/7/2.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class IntelWsServImpl implements IntelWsServ {


    @Override
    public String sayHello(String userName) {
        return "hello " + userName;
    }
}
