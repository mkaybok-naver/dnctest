package com.dnc.dispatcher.rest.home.controller;

import org.hibernate.dialect.SQLiteDialect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <pre>
 * com.dnc.dispatcher.rest.home.controller
 * HomeController.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Controller
public class HomeController {
	
	@GetMapping(value = "/home/")
	@ResponseBody
	public String index() {
		return "dandc rest api home";
	}
}
