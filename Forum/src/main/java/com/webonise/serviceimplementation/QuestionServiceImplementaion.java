package com.webonise.serviceimplementation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webonise.dao.LoginDao;
import com.webonise.dao.QuestionDao;
import com.webonise.models.Question;
import com.webonise.service.QuestionService;

@Service
public class QuestionServiceImplementaion implements QuestionService {

	private static final Logger logger = Logger.getLogger(QuestionServiceImplementaion.class);

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private LoginDao loginDao;

	/*
	 * @Autowired private CacheServiceImpl cacheServiceImpl;
	 */
	@Override
	public List<Question> getAllQuestions() {

		logger.info("Inside Question Serv");
		List<Question> questionList = questionDao.findAll();

		return questionList;
	}

	@Override
	public List<Question> getCacheQuestions() {

		List<Question> questionList = null;

		// Map map = cacheServiceImpl.readAllHash("QUESTION");

		/*
		 * if (!map.isEmpty()) {
		 * 
		 * questionList = new ArrayList<Question>();
		 * 
		 * for (Object entry : map.keySet()) {
		 * 
		 * questionList.add((Question)map.get(entry)); }
		 * 
		 * 
		 * 
		 * } else {
		 */
		questionList = questionDao.findAll();

		logger.info("This is list of all question \n" + questionList.iterator());

		return questionList;
	}

	@Override
	public Question getQuestionById(long questionId) {

		Question question = questionDao.findByQuestionId(questionId);
		return question;
	}

	@Override
	public void addQuestion(Question question, String userName) {

		question.setUser(loginDao.findByUserName(userName));

		questionDao.saveAndFlush(question);

		// cacheServiceImpl.save(saveAndFlush);

	}

	@Override
	public List<Question> searchQuestion(Question question) {

		List<Question> questionList = questionDao.findByQuestion(question.getQuestion());
		return questionList;
	}

	@Override
	public void deleteQuestion(long questionId) {

		// cacheServiceImpl.delete("QUESTION",questionId);

		questionDao.delete(questionId);
	}

	public void setQuestionDao(QuestionDao mockDao) {

		this.questionDao = mockDao;
	}

	@Override
	public boolean isQualified(long questionId, String userName) {
		if (questionDao.findByQuestionId(questionId).getUser().getUsername().equals(userName)) {

			return true;
		} else
		{
			return false;
		}
	}

	public void setLoginDao(LoginDao mockLoginDao) {
		// TODO Auto-generated method stub
		
		this.loginDao = mockLoginDao;
		
	}

}
