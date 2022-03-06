package com.smoothstack.quizapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "quizzes")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Quiz(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + "]";
	}
	
}
