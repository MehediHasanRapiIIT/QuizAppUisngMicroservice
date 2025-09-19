package com.quiz.controllers;


import com.quiz.entities.Quiz;
import com.quiz.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    //create

    @PostMapping("quiz")
    public Quiz create(@RequestBody Quiz quiz){
        return quizService.add(quiz);
    }

    @GetMapping("quiz")
    public List<Quiz> get(){
        return quizService.get();
    }
    @GetMapping("quiz/{id}")
    public Quiz getOne(@PathVariable Long id){
        return quizService.get(id);
    }
}
