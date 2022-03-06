package com.smoothstack.quizapp.services;

import java.util.List;
import java.util.Optional;

import com.smoothstack.quizapp.repos.AnswerRepo;
import com.smoothstack.quizapp.repos.QuestionRepo;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.smoothstack.quizapp.entities.Quiz;
import com.smoothstack.quizapp.repos.QuizRepo;

@Service
public class QuizService {
	
	@Autowired
	QuizRepo quizRepo;

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	AnswerRepo answerRepo;


	public Optional<Quiz> getQuizById(Integer quizId){
		return quizRepo.findById(quizId);
	}

	public void addQuizById(Quiz quiz) {
		quizRepo.save(quiz);
	}

}
