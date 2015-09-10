package com.webonise.serviceimplementation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.dao.LoginDao;
import com.webonise.dao.QuestionDao;
import com.webonise.models.Question;
import com.webonise.service.QuestionService;

@Service
public class QuestionServiceImplementaion implements QuestionService {

	private static final Logger LOGGER = Logger.getLogger(QuestionServiceImplementaion.class);

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private LoginDao loginDao;

	@Override
	public List<Question> getAllQuestions() throws ForumException {

		List<Question> questionList = questionDao.findAll();
		return questionList;
	}
	
	@Override
	public List<Question> getCacheQuestions() throws ForumException{

		List<Question> questionList = questionDao.findAll();
		LOGGER.info("This is list of all question \n" + questionList.iterator());
		return questionList;
	}

	@Override
	public Question getQuestionById(long questionId) throws ForumException {

		Question question = questionDao.findByQuestionId(questionId);
		
		if(question == null) {
			
			throw new ForumException("Question Not Found");
		}
		
		return question;
	}

	@Override
	public void addQuestion(Question question, String userName) throws ForumException{

		question.setUser(loginDao.findByUserName(userName));
		questionDao.saveQuestion(question);
	}

	@Override
	public List<Question> searchQuestion(Question question) throws ForumException{

		if(question == null){
			
			throw new ForumException("Question not provided to search");
		}
		
		List<Question> questionList = questionDao.findByQuestion(question.getQuestion());
		return questionList;
	}

	@Override
	public void deleteQuestion(long questionId) throws ForumException{

		questionDao.delete(questionId);
	}

	public void setQuestionDao(QuestionDao questionDao) {

		this.questionDao = questionDao;
	}

	@Override
	public boolean isQualified(long questionId, String userName) throws ForumException{
		if (questionDao.findByQuestionId(questionId).getUser().getUsername().equals(userName)) {

			return true;
		} else {
			return false;
		}
	}

	public void setLoginDao(LoginDao mockLoginDao) {

		this.loginDao = mockLoginDao;
	}
}
