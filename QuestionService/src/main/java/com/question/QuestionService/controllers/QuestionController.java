package com.question.QuestionService.controllers;


import com.question.QuestionService.entities.Question;
import com.question.QuestionService.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping("question")
    public Question create(@RequestBody Question question){
        return questionService.create(question);
    }

    @GetMapping("question")
    public List<Question> get(){
        return questionService.get();
    }

    @GetMapping("question/{id}")
    public Question get(@PathVariable Long id){
        return questionService.getOne(id);
    }

    @GetMapping("question/quiz/{quizId}")
    public List<Question> getQuestionByQuiz(@PathVariable Long quizId){
        return questionService.getQuestionByQuiz(quizId);
    }
}
