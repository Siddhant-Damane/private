package com.webonise.dao;

import java.util.List;

import com.webonise.models.Question;

public interface QuestionDao {

	Question findByQuestionId(long questionId);

	
	List<Question> findByQuestion(String question);


	List<Question> findAll();


	void saveAndFlush(Question question);


	void delete(long questionId);

}
