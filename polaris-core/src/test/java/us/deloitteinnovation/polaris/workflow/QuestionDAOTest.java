package us.deloitteinnovation.polaris.workflow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.workflow.dao.IQuestionDAO;
import us.deloitteinnovation.polaris.workflow.dao.internal.QuestionDAOImpl;
import us.deloitteinnovation.polaris.workflow.model.Question;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mgundlapally on 10-05-2017.
 */
@Component
public class QuestionDAOTest extends AbstractTest {


    private IQuestionDAO iQuestionDAO;

    @Before
    public void setup() {
        iQuestionDAO = new QuestionDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void testgetQuestionAll() {
        List<Question> questionList = iQuestionDAO.getAllQuestions();
        Assert.assertNotNull(questionList);
        Assert.assertTrue(questionList.size() > 0);
    }


    @Test
    public void testgetQuestion() throws SQLException {
        Question question = QutionDataUtil.getQuestion(1, "Quarter Filter", "polarisdev", true);
        Question result = iQuestionDAO.getQuestion(1);
        Assert.assertNotEquals(question,result);
    }

    @Test
    public void testgetUpdate() throws SQLException {
        Question question = QutionDataUtil.getQuestion(1, "Quarter Filter", "polarisdev", true);
        Question question1= iQuestionDAO.updateQuestion(1,question);
        Assert.assertNotEquals(question,question1);

    }


    @Test
    public void testgetuMaxsort() throws SQLException {
        Question question = QutionDataUtil.getQuestion(1, "Quarter Filter", "polarisdev", true);
        Short sortIndex= iQuestionDAO.getMaxQuestionSortIndex(1);
        Assert.assertNotEquals(question,sortIndex);

    }


}
