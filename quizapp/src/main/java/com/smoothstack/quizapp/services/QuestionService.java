package com.smoothstack.quizapp.services;

import com.smoothstack.quizapp.entities.Question;
import com.smoothstack.quizapp.repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public List<Question> findQuestionsByQuizId(Integer quizId) {
        return questionRepo.findByquizzesid(quizId);
    }

}
