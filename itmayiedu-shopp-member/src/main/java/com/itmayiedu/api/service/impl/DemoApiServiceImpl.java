
package com.itmayiedu.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.api.service.DemoApiService;
import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.common.redis.BaseRedisService;

@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService {
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public Map<String, Object> demo() {
		return setResutSuccess();
	}

	@Override
	public Map<String, Object> setKey(@PathVariable String key, @PathVariable String value) {
		baseRedisService.setString(key, value);
		return setResutSuccess();

	}

	@Override
	public Map<String, Object> setKey2(@RequestParam("key") String key, @RequestParam("value") String value) {
		baseRedisService.setString(key, value);
		return setResutSuccess();

	}

	@Override
	public Map<String, Object> getKey(String key) {
		String value = baseRedisService.get(key);
		return setResutSuccessData(value);

	}

}
