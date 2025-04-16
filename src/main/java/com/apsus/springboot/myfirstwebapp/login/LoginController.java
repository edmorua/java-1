package com.apsus.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	//login first
	private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("login-text")
	@ResponseBody
	public String login1() {
		return "Login controller 1";
	}
	
	@RequestMapping("login-html")
	@ResponseBody
	public String loginHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Login </title>");
		sb.append("</head");
		sb.append("<body>");
		sb.append("<p> Login page </p>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	@RequestMapping("login")
	public String loginJSP(@RequestParam String name, ModelMap model) {
		logger.debug("Request Param is  [{}]", name);
		model.put("name", name);
		return "login";
	}
	
}
