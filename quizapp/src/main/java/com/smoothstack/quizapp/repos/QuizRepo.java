package com.smoothstack.quizapp.repos;

import org.springframework.data.repository.*;

import com.smoothstack.quizapp.entities.Quiz;

public interface QuizRepo extends CrudRepository<Quiz, Integer> {

}
