package com.webonise.service;

import java.util.List;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Question;

public interface QuestionService {

	public List<Question> getAllQuestions() throws ForumException;

	public Question getQuestionById(long questionId) throws ForumException;

	public void addQuestion(Question question, String userName) throws ForumException;

	public List<Question> searchQuestion(Question question) throws ForumException;

	public void deleteQuestion(long questionId) throws ForumException;

	List<Question> getCacheQuestions() throws ForumException;

	public boolean isQualified(long questionId, String userName) throws ForumException;
}
