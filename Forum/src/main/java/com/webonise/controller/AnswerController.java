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

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Answers;
import com.webonise.service.AnswerService;

@Controller
public class AnswerController {

	private static final Logger LOGGER = Logger.getLogger(AnswerController.class);

	private String userName;

	private String message;

	@Autowired
	private AnswerService answerService;

	@ModelAttribute
	public void getUserName(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {

			userName = authentication.getName();
		}
		model.addAttribute("userName", userName);
	}

	@RequestMapping(value = "/question/{questionId}/answers/", method = RequestMethod.POST)
	public String addAnswer(@PathVariable("questionId") long questionId, @ModelAttribute("answers") Answers answer,
			Model model, @RequestHeader(value = "referer", required = false) final String url) {

		try {
			answerService.addAnswer(answer, questionId, userName);
			message = "Answer Added Successfully !";

		} catch (ForumException e) {

			message = e.getMessage();
		} catch (Exception e) {
			message = "Something went wrong please try again !";
		}
		model.addAttribute("message", message);
		int index = url.indexOf('?') - 1;
		if (index > 0) {
			String newUrl = url.substring(0, index + 1);

			return "redirect:" + newUrl;
		} else
			return "redirect:" + url;
	}

	@RequestMapping(value = "/question/{questionId}/answers/{answerId}", method = RequestMethod.DELETE)
	public String deleteAnswer(@PathVariable("answerId") long answerId,
			@RequestHeader(value = "referer", required = false) final String url, Model model) {

		try {
			if (answerService.isQualified(answerId, userName) || userName.equals("admin")) {

				answerService.deleteAnswer(answerId);
				message = "Answer Deleted Successfully !";
				model.addAttribute("message", message);
				int index = url.indexOf('?') - 1;
				if (index > 0) {
					String newUrl = url.substring(0, index + 1);
					return "redirect:" + newUrl;
				} else
					return "redirect:" + url;
			} else {
				return "redirect:/403";
			}
		} catch (ForumException e) {
			
			message = "Something went wrong please try again !";
			model.addAttribute("message", message);
			return "redirect:" + url;
		} 
	}

	@RequestMapping(value = "/question/{questionId}/answers/{answerId}", method = RequestMethod.PUT)
	public String updateAnswer(@PathVariable("answerId") long answerId, Model model,
			@ModelAttribute("answer") Answers answer,
			@RequestHeader(value = "referer", required = false) final String url) throws ForumException {		
		
		try {
			if (answerService.isQualified(answerId, userName) || userName.equals("admin")) {

				answerService.updateAnswer(answerId, answer.getAnswer());
				message = "Answer Updated Successfully !";
				model.addAttribute("message", message);
				int index = url.indexOf('?') - 1;
				if (index > 0) {
					String newUrl = url.substring(0, index + 1);
					return "redirect:" + newUrl;
				} else
					return "redirect:" + url;
			} else {
				return "redirect:/403";
			}
		} catch (ForumException e) {
			
			message = "Something went wrong please try again !";
			model.addAttribute("message", message);
			return "redirect:" + url;
		} 
		
		
	}
}
