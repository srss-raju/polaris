package us.deloitteinnovation.polaris.workflow.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.workflow.dao.IQuestionDAO;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.service.IQuestionService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
@Service
public class QuestionServiceImpl implements IQuestionService {

    private final IQuestionDAO questionDAO;

    @Autowired
    public QuestionServiceImpl(IQuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    @Transactional
    public Question insertQuestion(Question question) {
        question.setSortIndex((short) (questionDAO.getMaxQuestionSortIndex(question.getStoryId()) + 1));
        return questionDAO.insertQuestion(question);
    }

    @Override
    public Question getQuestion(Integer questionId) {
        return questionDAO.getQuestion(questionId);
    }

    @Override
    public List<Question> getQuestions() {
        return questionDAO.getAllQuestions();
    }

    @Override
    @Transactional
    public Question updateQuestion(Integer questionId, Question question) {
        return questionDAO.updateQuestion(questionId, question);
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        questionDAO.deleteQuestion(questionId);
    }
}
