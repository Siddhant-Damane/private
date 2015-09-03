package com.webonise.service;

import com.webonise.models.Answers;

public interface AnswerService {


	public void addAnswer(Answers answer, long questionId, String userName);

	public void deleteAnswer(long answerId);

	boolean updateAnswer(long answerId, String updatedAnswer);

	public boolean isQualified(long answerId, String userName);
	
	

}
