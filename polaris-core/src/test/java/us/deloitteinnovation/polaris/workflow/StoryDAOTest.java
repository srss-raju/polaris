package us.deloitteinnovation.polaris.workflow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.workflow.dao.IQuestionDAO;
import us.deloitteinnovation.polaris.workflow.dao.IStoryDAO;
import us.deloitteinnovation.polaris.workflow.dao.internal.QuestionDAOImpl;
import us.deloitteinnovation.polaris.workflow.dao.internal.StoryDAOImpl;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.model.Story;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mgundlapally on 10-05-2017.
 */
@Component
public class StoryDAOTest extends AbstractTest {

    private IStoryDAO storyDAO;

    @Before
    public void setup() {
        storyDAO = new StoryDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void testgetStoryList() {
        List<Story> storyList = storyDAO.getAllStory();
        Assert.assertNotNull(storyList);
        Assert.assertTrue(storyList.size() > 0);
    }
    @Test
    public void testgetStoryId() throws SQLException {
        Story story = StoryDataUtil.getStory(1, "Quarter Filter", "polarisdev", true);
        Story result = storyDAO.getStory(1);
        Assert.assertEquals(story.getId(),result.getId());

    }

    @Test
    public void testgetMaxId ()throws SQLException {
        Story story = StoryDataUtil.getStory(1, "Quarter Filter", "polarisdev", true);
        Short result = storyDAO.getMaxStorySortIndex(1);
        Assert.assertNotEquals(story,result);

    }




}
