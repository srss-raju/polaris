package us.deloitteinnovation.polaris.workflow.dao;

import us.deloitteinnovation.polaris.workflow.model.Question;

import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
public interface IQuestionDAO {

    List<Question> getAllQuestions();
    Question getQuestion(Integer questionId);
    Question insertQuestion(Question question);
    Question updateQuestion(Integer questionId, Question question);
    void deleteQuestion(Integer questionId);
    Short getMaxQuestionSortIndex(Integer storyId);
}
