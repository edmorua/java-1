package com.apsus.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {


	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		model.put("name", "admin");
		return "welcome";
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loginJSP() {
		return "login";
	}

	
}
