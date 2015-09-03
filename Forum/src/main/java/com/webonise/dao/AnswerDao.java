package com.webonise.dao;

import java.util.List;


import org.springframework.stereotype.Repository;
import com.webonise.models.Answers;


public interface AnswerDao  {

	List<Answers> findByQuestionId(long questionId);

	Answers findByAnswerId(long answerId);

	void saveAndFlush(Answers answer);

	Answers findOne(long answerId);

	void delete(long answerId);

	void saveAndUpdate(Answers answer);

}