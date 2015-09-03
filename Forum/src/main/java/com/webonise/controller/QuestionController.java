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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webonise.models.Answers;
import com.webonise.models.Question;
import com.webonise.service.AnswerService;
import com.webonise.service.QuestionService;

@Controller

public class QuestionController {

	private static final Logger logger = Logger.getLogger(QuestionController.class);

	private String userName = "";

	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionService questionService;

	@ModelAttribute
	public void getUserName(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userName = authentication.getName();
		model.addAttribute("userName", userName);
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question, Model model) {

		questionService.addQuestion(question, userName);
		return "redirect:/";
	}

	@RequestMapping(value="/question",  method = RequestMethod.GET)
	public ModelAndView displayAllQuestions(Model model) {

		List<Question> questionList = questionService.getAllQuestions();

		return new ModelAndView("listAllQuestions", "questions", questionList);
	}

	@RequestMapping(value="/question/{questionId}",  method = RequestMethod.GET)
	public ModelAndView displayQuestion(@PathVariable("questionId") int questionId, Model model) {

		Question question = questionService.getQuestionById(questionId);
		model.addAttribute("questionId", questionId);
		return new ModelAndView("displayQuestion", "question", question);
	}

	@RequestMapping(value="/question/{questionId}/answers",  method = RequestMethod.GET)
	public ModelAndView displayMyAnswer(@PathVariable("questionId") long questionId, Model model) {

		model.addAttribute("question", questionService.getQuestionById(questionId));

		model.addAttribute("answer", new Answers());
		return new ModelAndView("displayAnswer");
	}

	@RequestMapping(value = "/searchQuestion", method = RequestMethod.GET)
	public String searchQuestion(@ModelAttribute("question") Question question, Model model) {

		List<Question> questions = questionService.searchQuestion(question);
		model.addAttribute("questions", questions);
		model.addAttribute("userQuestion", question.getQuestion());
		return "searchedQuestion";
	}

	@RequestMapping(value = "/question/{questionId}",  method = RequestMethod.DELETE)
	public String deleteQuestion(@PathVariable("questionId") long questionId ) {

		if (questionService.isQualified(questionId, userName) || userName.equals("admin")) {
			questionService.deleteQuestion(questionId);
			return "redirect:/";
		}
		else {

			return "redirect:/403";
		}
	}

}
