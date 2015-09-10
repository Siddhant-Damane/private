package com.webonise.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webonise.custom.exceptions.ForumException;
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

	@Override
	public void addAnswer(Answers answer, long questionId, String userName) throws ForumException {

		answer.setUser(loginDao.findByUserName(userName));
		answer.setQuestion(questionDao.findByQuestionId(questionId));
		
		if(answer.getQuestion() == null )
		{
			throw new ForumException("Error during setting Question");
			
		}
		else if (answer.getUser() == null) {
			
			throw new ForumException("Error during setting User");
		}
		
		answerDao.saveAnser(answer);
	}

	@Override
	public void deleteAnswer(long answerId) throws ForumException {

		answerDao.delete(answerId);
	}

	public boolean updateAnswer(long answerId, String updatedAnswer) throws ForumException {

		Answers answer = answerDao.findByAnswerId(answerId);
		
		if(answer == null ){
			
			throw new ForumException("Answer Does not exists");
		}
		
		answer.setAnswer(updatedAnswer);
		answerDao.saveAndUpdate(answer);
		return true;
	}

	@Override
	public boolean isQualified(long answerId, String userName) throws ForumException{

		if (answerDao.findByAnswerId(answerId).getUser().getUsername().equals(userName)) {
			return true;
		} else
			return false;
	}

	public void setAnswerDao(AnswerDao mockDao) {

		this.answerDao = mockDao;
	}

	public void setQuestionDao(QuestionDao mockQuestionDao) {

		this.questionDao = mockQuestionDao;
	}

	public void setLoginDao(LoginDao mockLoginDao) {

		this.loginDao = mockLoginDao;
	}

}
