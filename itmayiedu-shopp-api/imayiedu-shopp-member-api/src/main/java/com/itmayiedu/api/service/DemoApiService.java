package com.itmayiedu.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/demo")
public interface DemoApiService {
	/**
	 * 
	 * @methodDesc: 功能描述:(服务demo)
	 * @param: @return
	 */
	@GetMapping("/demo")
	public Map<String, Object> demo();
	//URL传参方式：
    //1、url:http://locahost:8762/demo/setKey/my_key/myValue，对应的注解是PathVariable,参数名要与注解中的key,value一致
	@RequestMapping("/setKey/{key}/{value}")
    public Map<String, Object> setKey(@PathVariable String key, @PathVariable String value);
    //2、url:http://localhost:8762/demo/setKey2?key=shop_test&value=test，RequestParam括号中指定的名称要与请求参数中的参数名一致，参数名可以不一致
    @RequestMapping("/setKey2")
    public Map<String, Object> setKey2(@RequestParam("key") String key, @RequestParam("value") String value);
	@GetMapping("/getKey")
    public Map<String, Object> getKey(String key);
}
