package com.webonise.service;

import java.util.List;

import com.webonise.models.Answers;

public interface AnswerService {

	public List<Answers> getAsnwerByQuestionId(long questionId);

	public void addAnswer(Answers answer, long questionId, String userName);

	public void deleteAnswer(long answerId);

	boolean updateAnswer(long answerId, String updatedAnswer);

	public boolean isQualified(long answerId, String userName);
	
	

}
