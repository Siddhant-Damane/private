package com.webonise.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answers")

public class Answers implements Serializable{

	private static final long serialVersionUID = -1104332242612789528L;

	@Id
	@GeneratedValue
	@Column(name = "answerid")
	private long answerId;

	@Column(name = "answer")
	private String answer;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "userid", nullable = false)
	 private Users user;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionid")
	private Question question;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


}
