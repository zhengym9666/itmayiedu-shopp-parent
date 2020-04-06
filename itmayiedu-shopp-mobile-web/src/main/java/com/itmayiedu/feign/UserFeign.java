
package com.itmayiedu.feign;



import com.itmayiedu.api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/*客户端负载均衡fein，注解member表示负责member服务的负载均衡*/
@FeignClient("member")
public interface UserFeign  extends UserService {
//	/**
//	 * 
//	 * @methodDesc: 功能描述:(使用token查找用户信息)
//	 * @param: @param
//	 *             token
//	 * @param: @return
//	 */
//	@PostMapping("/member/getUser")
//	public Map<String, Object> getUser(@RequestParam("token") String token);
}
