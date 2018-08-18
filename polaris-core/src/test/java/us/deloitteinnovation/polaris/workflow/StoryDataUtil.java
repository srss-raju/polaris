package us.deloitteinnovation.polaris.workflow;

import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundlapally on 5/3/2017.
 */
public class StoryDataUtil {

    public static Story getStory(Integer storyId, String moduleId, String title, boolean privateFlag) {
        Story story = new Story();
        if (null != title) {
            story.setTitle(title);
        }
        story.setId(1);
        story.setModuleId(2);
        story.setDisabled(privateFlag);
        story.setDisabled(false);
        return  story;
    }

    public static List<Story> getStoryList(int StoryCount) {
        List<Story> stories = new ArrayList<>();
        for (int i = 0; i < StoryCount; i++) {
            stories.add(StoryDataUtil.getStory(null, "link name " + i, "polarisdev", Boolean.TRUE));
        }
        return stories;
    }
}
