package com.smoothstack.quizapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String answer;

    @Column
    Integer correctanswer;

    @Column
    Integer answertype;

    @Column
    Integer questionsid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(Integer correctanswer) {
        this.correctanswer = correctanswer;
    }

    public Integer getAnswertype() {
        return answertype;
    }

    public void setAnswertype(Integer answertype) {
        this.answertype = answertype;
    }

    public Integer getQuestionsid() {
        return questionsid;
    }

    public void setQuestionsid(Integer questionsid) {
        this.questionsid = questionsid;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", correctanswer=" + correctanswer +
                ", answertype=" + answertype +
                ", questionsid=" + questionsid +
                '}';
    }
}
