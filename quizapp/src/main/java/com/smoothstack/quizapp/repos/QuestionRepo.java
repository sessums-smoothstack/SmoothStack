package com.smoothstack.quizapp.repos;

import com.smoothstack.quizapp.entities.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepo extends CrudRepository<Question, Integer> {


    //List<Question> findAllbyId(Long quizInt);
    List<Question> findByquizzesid(Integer quizId);
}
