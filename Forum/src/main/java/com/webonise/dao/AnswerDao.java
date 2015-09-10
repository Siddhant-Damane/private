package com.webonise.dao;

import java.util.List;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Answers;

public interface AnswerDao  {

	List<Answers> findByQuestionId(long questionId) throws ForumException;

	Answers findByAnswerId(long answerId)throws ForumException;

	void saveAnser(Answers answer)throws ForumException;

	Answers findOne(long answerId)throws ForumException;

	void delete(long answerId)throws ForumException;

	void saveAndUpdate(Answers answer)throws ForumException;

}