package us.deloitteinnovation.polaris.workflow.service;

import us.deloitteinnovation.polaris.workflow.model.Question;

import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
public interface IQuestionService {

    Question insertQuestion(Question question);
    Question getQuestion(Integer questionId);
    List<Question> getQuestions();
    Question updateQuestion(Integer questionId, Question question);
    void deleteQuestion(Integer questionId);
}
