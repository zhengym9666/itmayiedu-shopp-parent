
package com.itmayiedu.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.controller.BaseController;



@Controller
public class DemoController  extends BaseController {

	private static Logger log = LoggerFactory.getLogger(DemoController.class);

	// index页面
	public static final String INDEX = "index";

	@RequestMapping("/index")
	public String index(HttpServletRequest request,String token) {
//		log.info(" 我的web工程搭建成功啦!,userName:{}",getUserEntity(token).getUserName());
		return INDEX;
	}
}
