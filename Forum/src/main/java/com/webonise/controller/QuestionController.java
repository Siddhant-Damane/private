package com.webonise.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Answers;
import com.webonise.models.Question;
import com.webonise.service.AnswerService;
import com.webonise.service.QuestionService;

@Controller
public class QuestionController {

	private String userName = "";
	private String message = "";

	private static final Logger LOGGER = Logger.getLogger(AnswerController.class);

	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionService questionService;

	@ModelAttribute
	public void getUserName(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {

			userName = authentication.getName();
		}
		model.addAttribute("userName", userName);
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question, Model model) {

		try {
			questionService.addQuestion(question, userName);

			message = "Question Added Successfully !";

		} catch (ForumException e) {
			message = "Something went wrong please try again !";
			
		}
		model.addAttribute("message", message);

		return "redirect:/";
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView displayAllQuestions(Model model) {

		List<Question> questionList = null;
		try {
			questionList = questionService.getAllQuestions();
			message = "";

		} catch (ForumException e) {
			message = "Something went wrong please try again !";
		}
		model.addAttribute("message", message);

		return new ModelAndView("listAllQuestions", "questions", questionList);
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
	public String displayQuestion(@PathVariable("questionId") int questionId, Model model) {

		
		
		try{
			Question question = questionService.getQuestionById(questionId);
			model.addAttribute("questionId", questionId);
			model.addAttribute("question", question);
			return "displayQuestion";
		
		} 
		catch(ForumException e)
		{
			LOGGER.error(e.getMessage(), e);
			model.addAttribute("message",e.getMessage());
			return "404";	
		}
	}

	@RequestMapping(value = "/question/{questionId}/answers", method = RequestMethod.GET)
	public String displayAnswer(@PathVariable("questionId") long questionId, Model model,
			@ModelAttribute Answers answers, @RequestParam(value = "message", required = false) String alert) {

		try {
			model.addAttribute("question", questionService.getQuestionById(questionId));
			model.addAttribute("message", alert);
			return "displayAnswer";
		} catch (ForumException e) {
			LOGGER.error(e.getMessage(), e);
			return "404";
			
		}
		
	}

	@RequestMapping(value = "/searchQuestion", method = RequestMethod.GET)
	public String searchQuestion(@ModelAttribute("question") Question question, Model model) {

		try {
			List<Question> questions = questionService.searchQuestion(question);
			model.addAttribute("questions", questions);
			model.addAttribute("userQuestion", question.getQuestion());
			return "searchedQuestion";
		} catch (ForumException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);
			return "404";
		}
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.DELETE)
	public String deleteQuestion(@PathVariable("questionId") long questionId, Model model) throws ForumException {

		if (questionService.isQualified(questionId, userName) || userName.equals("admin")) {

			try {
				questionService.deleteQuestion(questionId);
				message = "Question Deleted Successfully !";

			}
			
			catch (ForumException e) {
				message = "Something went wrong please try again !";
			}
			model.addAttribute("message", message);

			return "redirect:/";
		} else {
			return "redirect:/403";
		}
	}
}
