package com.question.QuestionService.services;

import com.question.QuestionService.entities.Question;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface QuestionService {


    Question create(Question question);

    List<Question> get();

    Question getOne(Long id);

    List<Question> getQuestionByQuiz(Long quizId);

}
