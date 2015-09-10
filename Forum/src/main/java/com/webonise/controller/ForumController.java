package com.webonise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Answers;
import com.webonise.models.Question;
import com.webonise.service.QuestionService;
import java.util.List;

@Controller
public class ForumController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayHomePage(Model model,@ModelAttribute Answers answers,@ModelAttribute Question question, @RequestParam(value="message", required = false) String alert  ) {

		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String userName = "";
		if (authentication != null) {

			userName = authentication.getName();
		}
		model.addAttribute("userName", userName);
		
		List<Question> questionList;
		try{
			questionList = questionService.getCacheQuestions();
		}
		catch(ForumException e){
			questionList = null;
			alert = "error while fetching questions"; 
		}
		model.addAttribute("listOfQuestionAnswer", questionList);
		model.addAttribute("message",alert);

		return "index";
	}
}