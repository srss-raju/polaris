package us.deloitteinnovation.polaris.workflow.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.workflow.dao.IStoryDAO;
import us.deloitteinnovation.polaris.workflow.model.Story;
import us.deloitteinnovation.polaris.workflow.service.IStoryService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
@Service
public class StoryServiceImpl implements IStoryService {

    private final IStoryDAO storyDAO;

    @Autowired
    public StoryServiceImpl(IStoryDAO storyDAO) {
        this.storyDAO = storyDAO;
    }

    @Override
    @Transactional
    public Story insert(Story story) {
        story.setSortIndex((short) (storyDAO.getMaxStorySortIndex(story.getModuleId()) + 1));
        return storyDAO.insertStory(story);
    }

    @Override
    public Story getStory(Integer storyId) {
        return storyDAO.getStory(storyId);
    }

    @Override
    public List<Story> getStories() {
        return storyDAO.getAllStory();
    }

    @Override
    @Transactional
    public Story updateStory(Integer storyId, Story story) {
        return storyDAO.updateStory(storyId, story);
    }

    @Override
    public void deleteStory(Integer storyId) {
        storyDAO.deleteStory(storyId);
    }
}
