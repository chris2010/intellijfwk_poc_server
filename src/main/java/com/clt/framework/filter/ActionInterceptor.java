package com.clt.framework.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.clt.framework.utils.ServletUtils;
import com.clt.weixin.WeChat;
import com.clt.weixin.oauth.Oauth;
import com.clt.weixin.oauth.PayV2;


/**
 * @description 登录拦截器
 * @author King_wangyao
 * @date 2014-2-22
 * @version 1.0.0
 * 
 */
public class ActionInterceptor  extends HandlerInterceptorAdapter{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static String X_R = "X-Requested-With";
	public final static String X_R_VALUE = "XMLHttpRequest";
	
	private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";
	
	private List<String> nofilters;
	
	/**
	 * 退出
	 */
	private List<String> canGets;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		String  context = request.getContextPath();
		String openID=request.getParameter("myOpenid");
		System.out.println(openID);
		logger.info("user do action url:{}",uri);
        
		HttpSession session = request.getSession();
			
		if(uri.contains("/mobile/")){
			//加载分享签名
//			JsApiSign.addJsapiInfo(request);
			// 拦截器逻辑开始
			String nameSpace = handler.getClass().getCanonicalName();
			String actionName = handler.getClass().getSimpleName();
			
			logger.info("nameSpace: {}",nameSpace);
			logger.info("actionName: {}",actionName);

			String openid = (String) session.getAttribute("myOpenid");
//			String openid = "opUgSt_6u7QYjaTS7S4PdfkvXepc";
			
			/******del s*******/
			session.setAttribute("myOpenid", openid);
			/******del e*******/
			
			// 如果已经有用户了，就直接跳转
			
			if (openid == null) {
				// 没有用户的情况
				// 微信用户高级接口所需
				String code = request.getParameter("code");
				if(code != null){
					// 看是否调用了高级接口，调用了的话在这里取得用户信息
					JSONObject jsonObject = JSONObject.fromObject(Oauth.getToken(code));
					openid = jsonObject.getString("openid");
					session.setAttribute("myOpenid", openid);
					logger.info("myOpenid is {}",openid);
					return super.preHandle(request, response, handler);
				} else {
					// 没有调用高级接口，那么就调用它
					// 获得现在的网址
					String currenturl = request.getRequestURL()+(request.getQueryString()==null?"":("?"+request.getQueryString()));
//					String currenturl = "http://www.baidu.com";
					// 构造高级接口地址
					@SuppressWarnings("static-access")
					String redirectUrl = WeChat.oauth.getCode(URLEncoder.encode(currenturl,"utf-8"), true);
					
					ServletUtils.redirectToUrl(request, response, redirectUrl);
				}
			}
			logger.error("------------  not find user  -----------------");
		} 
		
		return super.preHandle(request, response, handler);
	}

	/**
     * 请求code
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getCode(String redirectUri, boolean isNeedUserInfo) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", "wxbaf5917ea6f1cd7b");
        params.put("response_type", "code");
        params.put("redirect_uri", redirectUri);
        if(isNeedUserInfo){
        	params.put("scope", "snsapi_userinfo");
        } else {
        	params.put("scope", "snsapi_base");
        }
        // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
        // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        params.put("state", "wx#wechat_redirect");
        String para = PayV2.createSign(params, false);
        return CODE_URI + "?" + para;
    }

	
	/**
	 * 判定是否忽略，验证登录
	 * @param uri
	 * @param context
	 * @return
	 */
	private boolean isEg(String uri, String context) {
		
//		for(String url:nofilters){
//			if(uri.equals(context+url)){
//				return true;
//			}
//		}
		
		return false;
	}
	
	/**
	 * 判定是否忽略，验证登录
	 * @param uri
	 * @param context
	 * @return
	 */
	private boolean isCanget(String uri, String context) {
		
		for(String url:canGets){
			if(uri.equals(context+url)){
				return true;
			}
		}
		
		return false;
	}

	public List<String> getNofilters() {
		return nofilters;
	}

	public void setNofilters(List<String> nofilters) {
		this.nofilters = nofilters;
	}

	public List<String> getCanGets() {
		return canGets;
	}

	public void setCanGets(List<String> canGets) {
		this.canGets = canGets;
	}

}
