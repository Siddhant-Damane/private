package com.webonise.test;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webonise.dao.LoginDao;
import com.webonise.dao.QuestionDao;
import com.webonise.models.Question;
import com.webonise.models.Users;
import com.webonise.serviceimplementation.QuestionServiceImplementaion;

import junit.framework.TestCase;

public class QuestionServiceImplementaionTest extends TestCase {

	private Question question;
	
	private Users user;

	private QuestionServiceImplementaion questionService;

	private QuestionDao mockQuestionDao;
	
	private LoginDao mockLoginDao;
	
	

	@Override
	protected void setUp() {
		questionService = new QuestionServiceImplementaion();
		mockQuestionDao = createStrictMock(QuestionDao.class);
		mockLoginDao = createStrictMock(LoginDao.class);
		questionService.setQuestionDao(mockQuestionDao);
		questionService.setLoginDao(mockLoginDao);
		question = new Question();
		user = new Users();
	}

	@Override
	protected void tearDown() {
		questionService = null;
		question = null;
		user = null;
	}

	@Test
	public void testGetQuestionById() {

		question.setQuestion("what is Spring?");

		expect(mockQuestionDao.findByQuestionId(50)).andReturn(question);

		replay(mockQuestionDao);

		assertEquals("what is Spring?", questionService.getQuestionById(50).getQuestion());
		verify(mockQuestionDao);
	}

	@Test
	public void testGetAllQuestions() {

		question.setQuestion("What is JSP?");

		List<Question> questionList = new ArrayList<Question>(1);
		questionList.add(question);

		expect(mockQuestionDao.findAll()).andReturn(questionList);

		replay(mockQuestionDao);
		
		assertEquals("What is JSP?", questionService.getAllQuestions().get(0).getQuestion());
		verify(mockQuestionDao);
		
	}

	@Test

	public void testAddQuestion() {
		
		expect(mockLoginDao.findByUserName("test")).andReturn(user) ;
		
		mockQuestionDao.saveAndFlush(question);
		EasyMock.expectLastCall().once();
		EasyMock.replay(mockQuestionDao);
		EasyMock.verify();		
	}
	
	@Test
	public void testSearchQuestion(){
	
		question.setQuestion("What is DBMS?");
		List<Question> questionList = new ArrayList<Question>(1);
		questionList.add(question);
		expect(mockQuestionDao.findByQuestion("What is DBMS?")).andReturn(questionList);
		replay(mockQuestionDao);
		assertEquals("What is DBMS?", questionService.searchQuestion(question).get(0).getQuestion());
		verify(mockQuestionDao);
		
	}
	
	@Test
	public void testDeleteQuestion(){
		
		mockQuestionDao.delete(5);
		EasyMock.expectLastCall().once();
		EasyMock.replay(mockQuestionDao);
		EasyMock.verify();

	}
	
	@Test
	public void testIsQualified(){
		
		user.setUsername("test");
		question.setUser(user);
		expect(mockQuestionDao.findByQuestionId(2)).andReturn(question);
		replay(mockQuestionDao);		
		
		
		assertEquals(true, questionService.isQualified(2, user.getUsername()));
		
		EasyMock.verify();
		
		
		
	}

}