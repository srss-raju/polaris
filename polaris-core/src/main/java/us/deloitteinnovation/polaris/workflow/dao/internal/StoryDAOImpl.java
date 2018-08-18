package us.deloitteinnovation.polaris.workflow.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.workflow.dao.IStoryDAO;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.model.Story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static us.deloitteinnovation.polaris.workflow.dao.internal.QuestionDAOImpl.*;

/**
 * Created by rbentaarit on 8/12/2016.
 */
@Repository
public class StoryDAOImpl extends AbstractDAO implements IStoryDAO {

    protected static final String STORY_PKID = "storyPKId";
    protected static final String STORY_FK_MODULE_ID = "storyFKModuleId";
    protected static final String STORY_TITLE = "storyTitle";
    protected static final String STORY_SORT_INDEX = "storySortIndex";
    protected static final String STORY_IS_DISABLED = "storyIsDisabled";

    protected static final String STORY_ALIAS =  "dbo.app_Story.Id as " + STORY_PKID +", " +
            "dbo.app_Story.ModuleId as " + STORY_FK_MODULE_ID +", " +
            "dbo.app_Story.Title as " + STORY_TITLE +", " +
            "dbo.app_Story.SortIndex " + STORY_SORT_INDEX +", " +
            "dbo.app_Story.IsDisabled as " + STORY_IS_DISABLED +", " +

            "dbo.app_Question.Id as " + QUESTION_PKID +", " +
            "dbo.app_Question.StoryId as " + QuestionDAOImpl.QUESTION_FK_STORY_ID +", " +
            "dbo.app_Question.Title as " + QuestionDAOImpl.QUESTION_TITLE +", " +
            "dbo.app_Question.SortIndex as " + QuestionDAOImpl.QUESTION_SORT_INDEX +", " +
            "dbo.app_Question.IsDisabled as " + QuestionDAOImpl.QUESTION_IS_DISABLED +", " +
            "dbo.app_Question.BookmarkId as " + QuestionDAOImpl.QUESTION_BOOKMARK_ID +", " +
            "dbo.app_Question.Icon as " + QuestionDAOImpl.QUESTION_ICON + " ";

    private static final String MAX_INDEX = "SELECT MAX([SortIndex]) FROM [app_Story] where [app_Story].[ModuleId] = ?";
    private static final String GET_STORIES = "select " + STORY_ALIAS + "from dbo.app_Story left join dbo.app_Question on dbo.app_Story.Id=dbo.app_Question.StoryId  order by " + STORY_PKID;
    private static final String GET_STORY_BY_ID= "select " + STORY_ALIAS + "from dbo.app_Story left join dbo.app_Question on dbo.app_Story.Id=dbo.app_Question.StoryId where dbo.app_Story.Id=? order by " + STORY_PKID ;
    private static final String INSERT_STORY = "insert into dbo.app_Story (ModuleId,Title,SortIndex,IsDisabled) " +
            "output Inserted.id as "+ STORY_PKID +", Inserted.ModuleId as "+ STORY_FK_MODULE_ID +", Inserted.Title as "+ STORY_TITLE +", Inserted.SortIndex as "+ STORY_SORT_INDEX + ", Inserted.IsDisabled as " + STORY_IS_DISABLED +
            " values (?,?,?,?)";
    private static final String UPDATE_STORY =   "update dbo.app_Story " +
            "set ModuleId=?,Title=?,SortIndex=?,IsDisabled=? " +
            "where dbo.app_Story.Id=? ";
    private static final String DELETE_STORY = "delete from dbo.app_Story where dbo.app_Story.Id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Story> getAllStory() {
       return jdbcTemplate.query(GET_STORIES, rs -> {
           Map<Integer, Story> storyMap = new HashMap<>();
           Map<Integer, Question> questionMap = new HashMap<>();
           while (rs.next()) {
               int storyId = rs.getInt(STORY_PKID);
               if (!storyMap.containsKey(storyId)) {
                   storyMap.put(storyId, getStoryFromRs(rs));
               }
               int questionId = rs.getInt(QUESTION_PKID);
               if (!questionMap.containsKey(questionId)) {
                   questionMap.put(questionId, QuestionDAOImpl.getQuestionFromRs(rs));
               }
           }
           List<Story> stories = new ArrayList<>(storyMapper(storyMap, questionMap).values());
           stories.sort(Comparator.comparingInt(Story::getSortIndex));

           return stories;
       });
    }

    @Override
    public Story getStory(Integer storyId) {
        try {
            return jdbcTemplate.query(GET_STORY_BY_ID, new Object[]{storyId}, new StoryRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Story not found ", e);
            return null;
        }
    }

    @Override
    public Story insertStory(Story story) {
        return jdbcTemplate.query(INSERT_STORY, new Object[]{story.getModuleId(),story.getTitle(),story.getSortIndex(),story.getDisabled()},
                rs -> {
                    if (rs.next()){ return getStoryFromRs(rs);} return null;});
    }

    @Override
    public Story updateStory(Integer storyId, Story story) {
        jdbcTemplate.update(UPDATE_STORY, story.getModuleId(), story.getTitle(), story.getSortIndex(), story.getDisabled(), storyId);
        return getStory(storyId);
    }

    @Override
    public void deleteStory(Integer storyId) {
        jdbcTemplate.update(DELETE_STORY, storyId);
    }

    @Override
    public Short getMaxStorySortIndex(Integer moduleId){
        Short index = jdbcTemplate.queryForObject(MAX_INDEX, Short.class, moduleId);
        return  index != null ? index : 0;
    }

    protected static Story getStoryFromRs(ResultSet rs) throws SQLException{
            Story story = new Story();
            story.setId(rs.getInt(StoryDAOImpl.STORY_PKID));
            story.setModuleId(rs.getInt(StoryDAOImpl.STORY_FK_MODULE_ID));
            story.setTitle(rs.getString(StoryDAOImpl.STORY_TITLE));
            story.setSortIndex(rs.getShort(StoryDAOImpl.STORY_SORT_INDEX));
            story.setDisabled(rs.getBoolean(StoryDAOImpl.STORY_IS_DISABLED));
            return story;
        }

    protected static Map<Integer, Story> storyMapper (Map<Integer, Story> storyMap, Map<Integer, Question> questionMap ){
        for (Map.Entry<Integer, Story> storyIterator : storyMap.entrySet()){
            Story story = storyIterator.getValue();
            List<Question> questionList =
                    questionMap.entrySet().stream().filter(m -> m.getValue().getStoryId() == story.getId()).map(m -> m.getValue()).collect(Collectors.toList());
            questionList.sort(Comparator.comparingInt(Question::getSortIndex));
            story.setQuestionList(questionList);
            storyMap.put(storyIterator.getKey(), story);
        }
        return storyMap;
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Story");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }

    private class StoryRowMapper implements  ResultSetExtractor<Story>{
        @Override
        public Story extractData(ResultSet rs) throws SQLException {
            Map<Integer, Question> questionMap = new HashMap<>();
            Story story = null;
            while (rs.next()) {
                if (story == null){
                    story = getStoryFromRs(rs);
                }
                int questionId = rs.getInt(QUESTION_PKID);
                if (questionId != 0 && !questionMap.containsKey(questionId)){
                    questionMap.put(questionId, getQuestionFromRs(rs));
                }
            }
            List<Question> questionList = new ArrayList<>(questionMap.values());
            questionList.sort(Comparator.comparingInt(Question::getSortIndex));
            if (story != null){
                story.setQuestionList(questionList);
            }
            return story;
        }
    }
}
