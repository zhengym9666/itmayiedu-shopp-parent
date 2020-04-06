
package com.itmayiedu.constants;

public interface Constants {
	//用户会话保存90天
	Long USER_TOKEN_TERMVALIDITY = 60 * 60 * 24 * 90l;
	int WEBUSER_COOKIE_TOKEN_TERMVALIDITY = 1000*60 * 60 * 24 * 90;
	String USER_TOKEN = "token";

	String USER_SESSION_OPENID = "openid";
	String USER_SOURCE_QQ = "qq";
}
