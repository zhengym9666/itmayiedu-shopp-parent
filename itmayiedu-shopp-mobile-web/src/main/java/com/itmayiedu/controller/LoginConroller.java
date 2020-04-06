
package com.itmayiedu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.controller.BaseController;
import com.itmayiedu.constants.BaseApiConstants;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.feign.UserFeign;
import com.itmayiedu.web.CookieUtil;

@Controller
public class LoginConroller extends BaseController {
	private static final String LGOIN = "login";
	private static final String INDEX = "index";
	private static final String ERROR = "error";
	private static final String ASSOCIATEDACCOUNT = "associatedAccount";
	@Autowired
	private UserFeign userFeign;

	@RequestMapping("/locaLogin")
	public String locaLogin(String source,HttpServletRequest request) {
		request.setAttribute("source", source);
		return LGOIN;
	}

	@RequestMapping("/login")
	public String loginIn(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> login = userFeign.login(userEntity);
		Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
		if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
			String msg = (String) login.get("msg");
			return setError(request, msg, LGOIN);
		}
		// 登录成功之后,获取token.将这个token存放在cookie
		String token = (String) login.get("data");
		CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
		return INDEX;

	}

	/**
	 *
	 * @methodDesc: 功能描述:(生成qq授权)
	 * @param: @param
	 *             request
	 * @param: @return
	 * @param: @throws
	 *             QQConnectException
	 */
	@RequestMapping("/authorizeUrl")
	public String authorizeUrl(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws Exception {
		String authorizeUrl = new Oauth().getAuthorizeURL(request);
		return "redirect:" + authorizeUrl;
	}

	@RequestMapping("/qqLoginCallback")
	public String qqLoginCallback(HttpServletRequest request, HttpServletResponse response,HttpSession  httpSession) throws QQConnectException {
		// String code = request.getParameter("code");
		// 第一步获取授权码
		// 第二步获取accesstoken
		AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
		String accessToken = accessTokenObj.getAccessToken();
		if (StringUtils.isEmpty(accessToken)) {
			return setError(request, "QQ授权失败!", ERROR);
		}

		OpenID openidObj = new OpenID(accessToken);
		// 数据查找openid是否关联,如果没有关联先跳转到关联账号页面,如果直接登录.
		String userOpenId = openidObj.getUserOpenID();
		Map<String, Object> userLoginOpenId = userFeign.userLoginOpenId(userOpenId);
		Integer code = (Integer) userLoginOpenId.get(BaseApiConstants.HTTP_CODE_NAME);
		if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
			String token = (String) userLoginOpenId.get("data");
			CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
			return "redirect:/" + INDEX;
		}

		// 没有关联QQ账号
		httpSession.setAttribute(Constants.USER_SESSION_OPENID,userOpenId);
		return ASSOCIATEDACCOUNT;

	}

}
