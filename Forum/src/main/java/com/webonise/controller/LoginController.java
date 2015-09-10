package com.webonise.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webonise.custom.exceptions.ConstraintException;
import com.webonise.custom.exceptions.DataLimitException;
import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Users;
import com.webonise.service.LoginService;

@Controller
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public ModelAndView getLoginForm(@ModelAttribute Users users,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		String message = "";
		if (error != null) {
			message = "Incorrect username or password !";
		} else if (logout != null) {
			message = "Logout successful !";
			return new ModelAndView("redirect: /");
		}
		return new ModelAndView("login", "message", message);
	}

	@RequestMapping(value = "/signupprocess", method = RequestMethod.POST)
	public String singUpProcess(@ModelAttribute("users") Users users, Model model) {

		try {
			loginService.registerUser(users);
			model.addAttribute("message", "Sign Up Successfully, Please login to continue...");

		} catch (ConstraintException e) {
			LOGGER.error("Exception is  " + e.getMessage());
			model.addAttribute("message", e.getMessage());
			return "redirect:/signup?error";
		}
		catch (DataLimitException e) {
			LOGGER.error("Exception is ", e);
			model.addAttribute("message", e.getMessage());
			return "redirect:/signup?error";
		}
		
		catch (ForumException e) {

			LOGGER.error("Exception is ", e);
			model.addAttribute("message", e.getMessage());
			return "redirect:/signup?error";
		}
		return "redirect:/";
	}

	@RequestMapping("/signup")
	public String getUser(@ModelAttribute Users users, Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "message", required = false) String alert) {

		model.addAttribute("message", alert);

		return "signup";
	}

	@RequestMapping("/403")
	public String getAccessDenied(Model model) {
		String userName = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
			UserDetails userDetail = (UserDetails) authentication.getPrincipal();
			userName = userDetail.getUsername();
		}

		else {
			LOGGER.info("access handled by controller : " + userName);
			return "redirect:/";
		}

		model.addAttribute("userName", userName);
		return "403";
	}
	
	@RequestMapping("/404")
	public String getPageNotFound(Model model){
		
		
		String userName = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
			UserDetails userDetail = (UserDetails) authentication.getPrincipal();
			userName = userDetail.getUsername();
		}

		else {
			LOGGER.info("access handled by controller : " + userName);
			return "redirect:/";
		}

		model.addAttribute("userName", userName);
		
		
		
		return "404";
	}
}
