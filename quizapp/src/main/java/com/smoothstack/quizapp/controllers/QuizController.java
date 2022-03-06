package com.smoothstack.quizapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.smoothstack.quizapp.entities.Question;
import com.smoothstack.quizapp.services.AnswerService;
import com.smoothstack.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.smoothstack.quizapp.entities.Quiz;
import com.smoothstack.quizapp.services.QuizService;

@Controller
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	@RequestMapping(path = "/quiz/{quizId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Question> getQuizById(@PathVariable Integer quizId) {
		List<Question> qs = questionService.findQuestionsByQuizId(quizId);
		System.out.println(qs);
		return questionService.findQuestionsByQuizId(quizId);
	}

	@RequestMapping(path = "/quiz/{quizId}/allQuestions")
	public void getQuestionsByQuizId(@PathVariable Integer quizId) {
		questionService.findQuestionsByQuizId(quizId);
		answerService.getAllAnswersByQuestionId(quizId);
	}

	@PostMapping(path = "/quiz/save/")
	public void addQuizId(@RequestBody Quiz quiz) {
		System.out.println(quiz);
		quizService.addQuizById(quiz);
	}



}
