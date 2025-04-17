package com.apsus.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {


	private AuthenticationService authenticationService;

	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());
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
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loginJSP() {
		return "login";
	}
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String gotToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if(this.authenticationService.authenticate(name, password)) {
			logger.info("Login POST");
			model.put("name", name);
			return "welcome";
		}

		model.put("error", "Invalid credentials");
		return "login";
	}
	
}
