package us.deloitteinnovation.polaris.workflow.dao;

import us.deloitteinnovation.polaris.workflow.model.Story;

import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
public interface IStoryDAO {

    List<Story> getAllStory();
    Story getStory(Integer storyId);
    Story insertStory(Story story);
    Story updateStory(Integer storyId, Story story);
    void deleteStory(Integer storyId);
    Short getMaxStorySortIndex(Integer moduleId);

}
