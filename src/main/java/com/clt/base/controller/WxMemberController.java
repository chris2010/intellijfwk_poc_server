package com.clt.base.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.weixin.WeChat;
import com.clt.weixin.message.bean.UserInfo;
import com.clt.weixin.message.menu.Button;
import com.clt.weixin.message.menu.Menu;
import com.clt.weixin.oauth.Pay;
import com.clt.weixin.oauth.PayV2;
import com.clt.weixin.oauth.User;
import com.clt.weixin.util.JsApiSign;
import com.clt.weixin.util.ParseXmlUtil;
import com.clt.weixin.util.Tools;

@Controller
@RequestMapping("/mobile")
public class WxMemberController extends BaseController {
	protected Map<String, Object> jsonData = new LinkedHashMap<String, Object>();
	// 注入request
	protected HttpServletRequest request;
	// 注入response
	protected HttpServletResponse response;
	// 注入session
	protected HttpSession session;
	
	private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";

	protected void handRequest(HttpServletRequest req, HttpServletResponse resp) {
		setServletRequest(req);
		setServletResponse(resp);
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequestAttr(String name, Object obj) {
		request.setAttribute(name, obj);
	}

	@RequestMapping("/synWxMember")
	public @ResponseBody
	JSONObject synWxMember(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		handRequest(req, resp);
		List<String> list = WeChat.user.getAll();//获取所有的openid
		if (list != null && list.size() > 0) {
			for (String str : list) {
				UserInfo userInfo = User.getUserInfo(str);//获取用户的所有信息
			}
		}
//		if(access_token!=null){
//			jsonData.put("access_token", access_token);
//			return JSONObject.fromObject(jsonData);
//		}
//		String jsonStr = null;
////		try {
////			jsonStr = HttpKit.get(ACCESSTOKEN_URL
////					.concat("&appid=wxbaf5917ea6f1cd7b")
////					+ "&secret=b6eae9bdc939cbc1682acf200d1f81c3");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//
//		try {
//			jsonStr = HttpClientUtil.sendPostRequest(
//					ACCESSTOKEN_URL.concat("&appid=wxbaf5917ea6f1cd7b")
//							+ "&secret=b6eae9bdc939cbc1682acf200d1f81c3", "",
//					"UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		access_token="234";
//		System.out.println(jsonStr);
		// List<String> list = WeChat.user.getAll();
		return JSONObject.fromObject(jsonData);
	}


	@RequestMapping("/synWxMenu")
	public @ResponseBody
	JSONObject synWxMenu(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		handRequest(req, resp);
		Map<String, Object> map = WeChat.menu.getMenuInfo();//获取同步的菜单信息
		Set<String> set = map.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			Menu menu = (Menu) map.get(str);
			List<Button>list=menu.getButton();
			for(Button bt:list){
				bt.getName();
			}
		}
		return JSONObject.fromObject(jsonData);
	}
	
	
	/**
	 * 订单详情
	 */
	@RequestMapping("/paymentShow")
	public String paymentShow(HttpServletRequest req, HttpServletResponse resp) {
		handRequest(req,resp);
		
		String notify_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		
			Map<String, String> payInfoValue;
			try {
				int sum = 1;
				payInfoValue = Pay.payInfoGetMap(req, String.valueOf(sum), (String)session.getAttribute("myOpenid"), "", notify_url + File.separator + "/mobile/paySuccess","驿家商品");
				String payInfoStr = "";
				payInfoStr += "timestamp:'" + payInfoValue.get("timestamp");
				payInfoStr += "',nonceStr:'" + payInfoValue.get("nonceStr");
				payInfoStr += "',package:'" + payInfoValue.get("package");
				payInfoStr += "',signType:'" + payInfoValue.get("signType");
				payInfoStr += "',paySign:'" + payInfoValue.get("paySign") + "'";
				req.setAttribute("payInfoStr", payInfoStr);
				JsApiSign.addJsapiInfo(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return "mobile/paymentShow";
	}
	
	
	
	/**
	 * 订单详情
	 * @throws Exception 
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		handRequest(req,resp);
//		HttpSession session=req.getSession();
//		// 没有用户的情况
//		// 微信用户高级接口所需
//		String code = req.getParameter("code");
//		String openid="";
//		if(code != null){
//			// 看是否调用了高级接口，调用了的话在这里取得用户信息
//			JSONObject jsonObject = JSONObject.fromObject(Oauth.getToken(code));
//			openid = jsonObject.getString("openid");
//			session.setAttribute("myOpenid", openid);
//			return "mobile/index";
//		} else {
//			// 没有调用高级接口，那么就调用它
//			// 获得现在的网址
//			String currenturl = req.getRequestURL()+(req.getQueryString()==null?"":("?"+req.getQueryString()));
//			// 构造高级接口地址
//			
//			try {
//				@SuppressWarnings("static-access")
//				String redirectUrl = getCode(URLEncoder.encode(currenturl,"utf-8"), false);
//				ServletUtils.redirectToUrl(request, response, redirectUrl);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		//加载分享签名
////		JsApiSign.addJsapiInfo(req);
//		String openId=(String) session.getAttribute("myOpenid");
//		System.out.println("openId="+openId);
		
		return "mobile/index";
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
	
	//微信支付成功后回调
	@RequestMapping("/paySuccess")
	public void paySuccess(HttpServletRequest req, HttpServletResponse resp){
		handRequest(req,resp);
		
		String statucSuccess = "success";
		String statucFail = "fail";
		try {
			// post 过来的xml
			ServletInputStream in = request.getInputStream();
			String xmlMsg = Tools.inputStream2String(in);
			// 转换微信post过来的xml内容
			Map<String, String> paraMap = ParseXmlUtil.parseXml(xmlMsg);
			String openid = paraMap.get("openid");
			String trade_type = paraMap.get("trade_type");
			String totalFee = paraMap.get("total_fee");
			String orderId = paraMap.get("out_trade_no");
			String transId = paraMap.get("transaction_id");

			System.out.println("trade_state:\t" + trade_type + "totalFee:\t"
					+ totalFee + "orderId:\t" + orderId + "transId:\t" + transId);

			if (StringUtils.isEmpty(orderId)) {
				printScript(statucFail);
				return;
			}
			// 自己的业务逻辑 bg
					try {
						// 发送客服消息
						String messageResult = WeChat.message.sendText(openid, "您的订单号" + orderId + "已经支付成功！");
						printScript(statucSuccess);
					} catch (Exception e) {
						e.printStackTrace();
					
				} 
		}catch (Exception e) {
			e.printStackTrace();
			printScript(statucFail);
		}
	}
	
	/**
	 * @param content 相应的内容
	 * @Description 输出给接口
	 */
	public void printScript(String content) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) out.close();
		}
	}
	
	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}
}
