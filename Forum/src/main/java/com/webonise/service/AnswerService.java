package com.webonise.service;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.models.Answers;

public interface AnswerService {

	public void addAnswer(Answers answer, long questionId, String userName) throws ForumException;

	public void deleteAnswer(long answerId) throws ForumException;

	boolean updateAnswer(long answerId, String updatedAnswer) throws ForumException;

	public boolean isQualified(long answerId, String userName) throws ForumException;
}
