package com.webonise.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webonise.models.Answers;
import com.webonise.models.Question;
import com.webonise.service.QuestionService;



@Controller
public class ForumController {

	private static final Logger logger = Logger.getLogger(ForumController.class);


	@Autowired
	QuestionService questionService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String displayHomePage(Model model) {

		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     String userName = authentication.getName(); 
	     model.addAttribute("userName", userName);
		model.addAttribute("question", new Question());
		model.addAttribute("answer", new Answers());
		
		model.addAttribute("listOfQuestionAnswer", questionService.getCacheQuestions());

		return "index";
	}
}