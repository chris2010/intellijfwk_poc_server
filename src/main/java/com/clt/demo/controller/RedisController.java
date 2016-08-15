package com.clt.demo.controller;

import java.util.UUID;

import com.clt.base.exception.FrameworkException;
import com.clt.framework.bean.PVLogBean;


//import com.clt.web.dao.redis.TourMobileRedisDao;
//import com.clt.web.utils.RedisClientProperitiesEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liujinbang on 15/12/19.
 */
@Controller
@RequestMapping("/redisController")
public class RedisController {

	Logger logger = Logger.getLogger(RedisController.class);

	@RequestMapping("/getSetCRedisData")
	@ResponseBody
	public String getSetCRedisData() {

		PVLogBean bean  = new PVLogBean() ;
		
		
		bean.setId(UUID.randomUUID().toString());
		bean.setIpAddress("127.0.0.1");
		
		logger.info(bean);
		/*
		 * TourMobileRedisDao tourMobileRedisDao = new
		 * TourMobileRedisDao(RedisClientProperitiesEnum.order.getName()); try {
		 * tourMobileRedisDao.set("123", "123");
		 * System.out.println(tourMobileRedisDao.get("123") );
		 * 
		 * } catch (FrameworkException e) { e.printStackTrace(); }
		 * logger.error("test02");
		 */
		return "";
	}

}
