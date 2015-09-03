package com.webonise.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webonise.models.Answers;
import com.webonise.service.AnswerService;

@Controller
public class AnswerController {

	private static final Logger logger = Logger.getLogger(AnswerController.class);

	private String userName="";
	@Autowired
	AnswerService answerService;

	@ModelAttribute
	public void getUserName(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userName = authentication.getName();
		model.addAttribute("userName", userName);
		model.addAttribute("answer", new Answers());
	}

	@RequestMapping(value = "/question/{questionId}/answers/", method = RequestMethod.POST)
	public String addAnswer(@PathVariable("questionId") long questionId, @ModelAttribute("answer") Answers answer,
			Model model, @RequestHeader(value = "referer", required = false) final String url) {

		
		answerService.addAnswer(answer, questionId, userName);
		return "redirect:" + url;
	}

	@RequestMapping(value="/question/{questionId}/answers/{answerId}",  method = RequestMethod.DELETE)
	public String deleteAnswer(@PathVariable("answerId") long answerId,
			@ModelAttribute("answer") Answers answer,
			@RequestHeader(value = "referer", required = false) final String url) {
		
		
		if (answerService.isQualified(answerId, userName) || userName.equals("admin")) {
			
			logger.info("inside if");
			answerService.deleteAnswer(answerId);
			return "redirect:" + url;
		}

		else {
			
			logger.info("inside else");
			return "redirect:/403";
		}
		
	}

	@RequestMapping(value="/question/{questionId}/answers/{answerId}",  method = RequestMethod.PUT)
	public String updateAnswer(@PathVariable("answerId") long answerId,@PathVariable("questionId") long questionId, Model model, @ModelAttribute("answer") Answers answer) {

		
		

		if (answerService.isQualified(answerId, userName) || userName.equals("admin")) {
			
			answerService.updateAnswer(answerId, answer.getAnswer());
			logger.info("Answer id " + answerId + " with answer " + answer.getAnswer() + " is now entered" );
			
			return "redirect:/";
		}

		else {
			
			return "redirect:/403";
		}
	}

	
}
