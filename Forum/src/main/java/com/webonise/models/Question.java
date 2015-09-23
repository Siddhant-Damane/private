package com.webonise.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.webonise.cache.management.cache.Cachable;


@Entity
@Table(name = "questions")
public class Question implements Serializable, Cachable{

	private static final long serialVersionUID = 4392712718828502723L;

	public static final String OBJECT_KEY = "QUESTION";
	
	@Id
	@GeneratedValue
	@Column(name = "questionid")
	private long questionId;



	@ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "userid", nullable = false)
	 private Users user;

	@Column(name = "question")
	private String question;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
	private Set<Answers> answers = new HashSet<Answers>();
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answers> answers) {
		this.answers = answers;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	 @Override
	 public long getKey() {
	  return getQuestionId();
	 }

	 @Override
	 public String getObjectKey() {
	  return OBJECT_KEY;
	 }

}