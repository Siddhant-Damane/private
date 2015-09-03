package com.webonise.test;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import org.easymock.EasyMock;
import org.junit.Test;

import com.webonise.dao.AnswerDao;
import com.webonise.dao.LoginDao;
import com.webonise.dao.QuestionDao;
import com.webonise.models.Answers;
import com.webonise.models.Question;
import com.webonise.models.Users;
import com.webonise.serviceimplementation.AnswerServiceImplementaion;

import junit.framework.TestCase;

public class AnswerServiceImplementaionTest extends TestCase{
	
	private Answers answer;
	private Question question;
	private Users user;
	
	private AnswerServiceImplementaion answerService;
	
	private AnswerDao mockAnswerDao;
	private QuestionDao mockQuestionDao;
	private LoginDao mockLoginDao;
	private Answers mockAnswer;
	
	@Override
	protected void setUp() throws Exception {
		
		answerService = new AnswerServiceImplementaion();
		mockAnswerDao = createStrictMock(AnswerDao.class);
		answer = new Answers();
		question = new Question();
		user = new Users();
		mockAnswer = createStrictMock(Answers.class);
		mockQuestionDao = createStrictMock(QuestionDao.class);
		mockLoginDao = createStrictMock(LoginDao.class);
		answerService.setAnswerDao(mockAnswerDao);
		answerService.setQuestionDao(mockQuestionDao);
		answerService.setLoginDao(mockLoginDao);
	}
	
	@Override
	protected void tearDown() throws Exception {
		
		answerService = null;
		answer = null;
		question = null;
		user = null;		
	}

	
	@Test
	public void testAddAnswer(){
		
		question.setQuestion("what is Spring?");			
		expect(mockQuestionDao.findByQuestionId(50)).andReturn(question);
		EasyMock.replay(mockQuestionDao);
		user.setUsername("himani");
		expect(mockLoginDao.findByUserName("himani")).andReturn(user);
		EasyMock.replay(mockLoginDao);

		mockAnswer.setUser(user);
		EasyMock.expectLastCall();
		
		mockAnswer.setQuestion(question);
		EasyMock.expectLastCall();
			
		mockAnswerDao.saveAndFlush(answer);
		EasyMock.expectLastCall().once();
		EasyMock.replay(mockAnswerDao);
		EasyMock.verify();
	}
	
	@Test
	public void testDeleteAnswer(){
		
		answer.setAnswer("This is Test Answer.");		
		expect(mockAnswerDao.findByAnswerId(1)).andReturn(answer);
		
		expect(mockAnswerDao.findOne(1)).andReturn(answer);
		
		mockAnswerDao.delete(5);
		EasyMock.expectLastCall().once();
		EasyMock.replay(mockAnswerDao);
		EasyMock.verify();

	}
	
	@Test
	public void testUpdateAnswer(){
		
		answer.setAnswer("Enter my answer");
		expect(mockAnswerDao.findByAnswerId(2)).andReturn(answer);
		
		mockAnswer.setAnswer(answer.getAnswer());
		EasyMock.expectLastCall();
		
		mockAnswerDao.saveAndUpdate(answer);
		EasyMock.expectLastCall();
		EasyMock.replay(mockAnswerDao);
		EasyMock.verify();
	}
	@Test
	public void testIsQualified(){
		
		user.setUsername("test");
		answer.setUser(user);
		expect(mockAnswerDao.findByAnswerId(2)).andReturn(answer);
		replay(mockAnswerDao);		
		
		
		assertEquals(true, answerService.isQualified(2, user.getUsername()));
		
		EasyMock.verify();
		
		
		
	}
}
