package com.webonise.forum;

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
	}

	@RequestMapping(value = "/question/{questionId}/answers/addAnswer", method = RequestMethod.POST)
	public String addAnswer(@PathVariable("questionId") long questionId, @ModelAttribute("answer") Answers answer,
			Model model, @RequestHeader(value = "referer", required = false) final String url) {

		
		answerService.addAnswer(answer, questionId, userName);
		return "redirect:" + url;
	}

	@RequestMapping("/deleteAnswer/{answerId}")
	public String deleteAnswer(@PathVariable("answerId") long answerId,
			@RequestHeader(value = "referer", required = false) final String url) {
		if (answerService.isQualified(answerId, userName) || userName.equals("admin")) {
			answerService.deleteAnswer(answerId);
			return "redirect:" + url;
		}

		else {

			return "403";
		}
		
	}

	@RequestMapping("/updateAnswer/{answerId}")
	public String setUpdateAnswerModelAttribute(@PathVariable("answerId") long answerId, Model model) {

		model.addAttribute("answer", new Answers());

		if (answerService.isQualified(answerId, userName) || userName.equals("admin")) {
			return "updateAnswer";
		}

		else {
			
			return "403";
		}
	}

	@RequestMapping("/updateAnswerWithAnswerId/{answerId}")
	public String updateAnswer(@PathVariable("answerId") long answerId, @ModelAttribute("answer") Answers answer) {

		answerService.updateAnswer(answerId, answer.getAnswer());

		return "redirect:/";
	}

}
