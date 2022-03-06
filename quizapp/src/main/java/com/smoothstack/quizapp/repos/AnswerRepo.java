package com.smoothstack.quizapp.repos;

import com.smoothstack.quizapp.entities.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepo extends CrudRepository<Answer, Integer> {

}
