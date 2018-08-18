package us.deloitteinnovation.polaris.workflow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.alerts.dao.internal.AlertsDAOImpl;
import us.deloitteinnovation.polaris.alerts.service.IAlertsService;
import us.deloitteinnovation.polaris.alerts.service.internal.AlertsServiceImpl;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.workflow.dao.internal.StoryDAOImpl;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.model.Story;
import us.deloitteinnovation.polaris.workflow.service.IStoryService;
import us.deloitteinnovation.polaris.workflow.service.internal.StoryServiceImpl;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;

/**
 * Created by mgundlapally on 4/27/2017.
 */
@Component
public class IStoryServiceImpTest extends AbstractTest {

    @Mock
    private StoryDAOImpl storyDAO;

    private IStoryService iStoryService;

    @Before
    public void setup() {
        iStoryService = new StoryServiceImpl(storyDAO);
    }


    @Test
    public void getStories() {

        List<Story> story = StoryDataUtil.getStoryList(5);
        Mockito.when(storyDAO.getAllStory()).thenReturn(story);
        Assert.assertEquals(story, iStoryService.getStories());

    }



    @Test
    public void getStoryId() {

        Story  story = StoryDataUtil.getStory(1, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(storyDAO.getStory(anyInt())).thenReturn(story);
        Assert.assertEquals(story, iStoryService.getStory(anyInt()));
    }

    @Test
    public void getInsert() {
        Story story = StoryDataUtil.getStory(1, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(storyDAO.insertStory(story)).thenReturn(story);
        Story story1=  iStoryService.insert(story);
        Assert.assertNotNull(story1);
        Assert.assertEquals(story, story1);
    }
    @Test
    public void getUpdate() {

        Story story = StoryDataUtil.getStory(1, "link name", "polarisdev", Boolean.TRUE);
        Mockito.when(storyDAO.updateStory(1,story)).thenReturn(story);
        Story story1=  iStoryService.updateStory(1,story);
        Assert.assertNotNull(story1);
        Assert.assertEquals(story, story1);
    }
}
