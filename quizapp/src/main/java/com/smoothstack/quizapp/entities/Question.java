package com.smoothstack.quizapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String question;

    @Column
    Integer questiontype;

    @Column
    Integer quizzesid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(Integer questiontype) {
        this.questiontype = questiontype;
    }

    public Integer getQuizzesid() {
        return quizzesid;
    }

    public void setQuizzesid(Integer quizzesid) {
        this.quizzesid = quizzesid;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", questiontype=" + questiontype +
                ", quizzesid=" + quizzesid +
                '}';
    }
}
