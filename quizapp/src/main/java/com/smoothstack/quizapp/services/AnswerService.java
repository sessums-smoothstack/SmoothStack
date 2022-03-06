package com.smoothstack.quizapp.services;

import com.smoothstack.quizapp.repos.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    AnswerRepo answerRepo;

    public void getAllAnswersByQuestionId(Integer questionId) {
        System.out.println(answerRepo.findById(questionId));
    }
}
