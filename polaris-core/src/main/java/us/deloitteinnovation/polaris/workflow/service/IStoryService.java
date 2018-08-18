package us.deloitteinnovation.polaris.workflow.service;

import us.deloitteinnovation.polaris.workflow.model.Story;

import java.util.List;

/**
 * Created by rbentaarit on 8/12/2016.
 */
public interface IStoryService {
    Story insert(Story story);
    Story getStory(Integer storyId);
    List<Story> getStories();
    Story updateStory(Integer storyId, Story story);
    void deleteStory(Integer storyId);
}
