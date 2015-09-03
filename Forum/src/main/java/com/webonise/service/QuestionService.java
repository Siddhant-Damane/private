package com.webonise.service;

import java.util.List;

import com.webonise.models.Question;

public interface QuestionService {

	public List<Question> getAllQuestions();

	public Question getQuestionById(long questionId);

	public void addQuestion(Question question, String userName);

	public List<Question> searchQuestion(Question question);

	public void deleteQuestion(long questionId);

	List<Question> getCacheQuestions();

	public boolean isQualified(long questionId, String userName);

}
