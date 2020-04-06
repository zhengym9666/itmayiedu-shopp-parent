
package com.itmayiedu.manage;

import com.itmayiedu.entity.UserEntity;

import java.util.Map;

public interface UserServiceManage {
	/**
	 * 
	 * @methodDesc: 功能描述:(注册服务)
	 * @param: @param
	 *             UserEntity
	 */
	public void regist(UserEntity userEntity);
	
	public String md5PassSalt(String phone, String password);

	public Map<String, Object> login(UserEntity userEntity);

	public Map<String, Object> getUser(String token);

	public Map<String, Object> userLoginOpenId(String openid);

}
