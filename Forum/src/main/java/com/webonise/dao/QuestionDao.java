package com.webonise.dao;

import java.util.List;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Question;

public interface QuestionDao {

	Question findByQuestionId(long questionId)throws ForumException;

	List<Question> findByQuestion(String question)throws ForumException;

	List<Question> findAll()throws ForumException;

	void saveQuestion(Question question)throws ForumException;

	void delete(long questionId)throws ForumException;

}
