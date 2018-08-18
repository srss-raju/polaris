package us.deloitteinnovation.polaris.workflow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.bookmarks.BookmarkDataUtil;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.workflow.dao.internal.QuestionDAOImpl;
import us.deloitteinnovation.polaris.workflow.dao.internal.StoryDAOImpl;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.service.IQuestionService;
import us.deloitteinnovation.polaris.workflow.service.IStoryService;
import us.deloitteinnovation.polaris.workflow.service.internal.QuestionServiceImpl;
import us.deloitteinnovation.polaris.workflow.service.internal.StoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;

/**
 * Created by mgundlapally on 4/28/2017.
 */
@Component
public class QuestionServiceImpTest extends AbstractTest{

    @Mock
    private QuestionDAOImpl questionDAO;

    private IQuestionService  iQuestionService;



    @Before
    public void setup() {
        Question question= new Question();
        iQuestionService = new QuestionServiceImpl(questionDAO);
    }

    @Test
    public void getQuestions() {
        List<Question> questions = QutionDataUtil.getQuestionList(5);
        Mockito.when(questionDAO.getAllQuestions()).thenReturn(questions);
        Assert.assertEquals(questions, iQuestionService.getQuestions());

    }


    @Test
    public void getQuestionId() {

        Question question = QutionDataUtil.getQuestion(1, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(questionDAO.getQuestion(anyInt())).thenReturn(question);
        Assert.assertEquals(question, iQuestionService.getQuestion(anyInt()));
    }


    @Test
    public void getQuestionInsert() {

        Question question = QutionDataUtil.getQuestion(1, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(questionDAO.insertQuestion(question)).thenReturn(question);
        Question question1=  iQuestionService.insertQuestion(question);
         Assert.assertNotNull(question1);
        Assert.assertEquals(question, question1);
    }

    @Test
    public void getQuestionUpdate() {
       Question question = QutionDataUtil.getQuestion(1, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(questionDAO.updateQuestion(1,question)).thenReturn(question);
        Question question1=  iQuestionService.updateQuestion(1,question);
        Assert.assertNotNull(question1);
        Assert.assertEquals(question, question1);
    }

}
