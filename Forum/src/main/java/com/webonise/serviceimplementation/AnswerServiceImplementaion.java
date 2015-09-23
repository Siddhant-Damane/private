package com.webonise.serviceimplementation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webonise.dao.AnswerDao;
import com.webonise.dao.LoginDao;
import com.webonise.dao.QuestionDao;
import com.webonise.models.Answers;
import com.webonise.service.AnswerService;

@Service
public class AnswerServiceImplementaion implements AnswerService {

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private LoginDao loginDao;

	private static final Logger logger = Logger.getLogger(AnswerServiceImplementaion.class);

	// @Autowired
	// private CacheService cacheService;

	@Override
	public List<Answers> getAsnwerByQuestionId(long questionId) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void addAnswer(Answers answer, long questionId, String userName) {
		// TODO Auto-generated method stub

		answer.setUser(loginDao.findByUserName(userName));

		answer.setQuestion(questionDao.findByQuestionId(questionId));
		answerDao.saveAndFlush(answer);

		// Question question = (Question) cacheService.read("QUESTION",
		// questionId);

		// question.getAnswers().add(answers);

		// cacheService.save(question);

	}

	@Override
	public void deleteAnswer(long answerId) {
		// TODO Auto-generated method stub

		answerDao.findOne(answerId).getQuestion().getAnswers().remove(answerDao.findByAnswerId(answerId));

		answerDao.delete(answerId);

	}

	@Override
	public boolean updateAnswer(long answerId, String updatedAnswer) {
		// TODO Auto-generated method stub

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		Answers answer = answerDao.findByAnswerId(answerId);

		answer.setAnswer(updatedAnswer);
		answerDao.saveAndUpdate(answer);
		return true;

		// long questionId = answer.getQuestion().getQuestionId();
		// Question question = (Question) cacheService.read("QUESTION",
		// questionId);

		// question.getAnswers().add(answers);

		// cacheService.save(question);

	}

	@Override
	public boolean isQualified(long answerId, String userName) {
		// TODO Auto-generated method stub

		if (answerDao.findByAnswerId(answerId).getUser().getUsername().equals(userName)) {
			System.out.println("ans user is " + answerDao.findByAnswerId(answerId).getUser().getUsername() + "  logged in user is "+ userName);
			return true;
		} else
			System.out.println("ans user is " + answerDao.findByAnswerId(answerId).getUser().getUsername() + "  logged in user is "+ userName);
			return false;
	}

}
