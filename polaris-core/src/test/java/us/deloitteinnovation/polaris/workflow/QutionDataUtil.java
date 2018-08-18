package us.deloitteinnovation.polaris.workflow;

import us.deloitteinnovation.polaris.bookmarks.BookmarkDataUtil;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;
import us.deloitteinnovation.polaris.workflow.model.Question;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundlapally on 5/3/2017.
 */
public class QutionDataUtil {



    public static Question getQuestion(Integer questionId, String storyId, String title, boolean privateFlag) {
        Question question = new Question();
        if (null != title) {
            question.setTitle(title);
        }
          question.setId(1);
          question.setStoryId(2);
          question.setDisabled(privateFlag);
          question.setDisabled(false);
          question.setBookmarkId(1L);
          return  question;
    }

    public static List<Question> getQuestionList(int QuestionCount) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < QuestionCount; i++) {
            questions.add(QutionDataUtil.getQuestion(null, "link name " + i, "polarisdev", Boolean.TRUE));
        }
        return questions;
    }
}
