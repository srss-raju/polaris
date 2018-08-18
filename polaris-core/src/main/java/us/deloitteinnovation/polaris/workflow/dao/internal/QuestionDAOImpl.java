package us.deloitteinnovation.polaris.workflow.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.workflow.dao.IQuestionDAO;
import us.deloitteinnovation.polaris.workflow.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by rbentaarit on 8/12/2016.
 */
@Repository
public class QuestionDAOImpl extends AbstractDAO implements IQuestionDAO {

    protected static final String QUESTION_PKID = "questionPKId";
    protected static final String QUESTION_FK_STORY_ID = "questionFKStoryId";
    protected static final String QUESTION_TITLE = "questionTitle";
    protected static final String QUESTION_SORT_INDEX = "questionSortIndex";
    protected static final String QUESTION_IS_DISABLED = "questionIsDisabled";
    protected static final String QUESTION_BOOKMARK_ID = "questionBookmarkId";
    protected static final String QUESTION_ICON = "questionIcon";

    private static final String QUESTION_ALIAS = "dbo.app_Question.Id as " + QUESTION_PKID +", " +
            "dbo.app_Question.StoryId as " +QUESTION_FK_STORY_ID +", " +
            "dbo.app_Question.Title as " + QUESTION_TITLE +", " +
            "dbo.app_Question.SortIndex as " + QUESTION_SORT_INDEX +", " +
            "dbo.app_Question.IsDisabled as " + QUESTION_IS_DISABLED +", " +
            "dbo.app_Question.BookmarkId as " + QUESTION_BOOKMARK_ID +", " +
            "dbo.app_Question.Icon as " + QUESTION_ICON + " ";
    private static final String MAX_INDEX = "SELECT MAX([SortIndex]) FROM [app_Question] WHERE [StoryId]=?";
    private static final String GET_QUESTIONS = "select " + QUESTION_ALIAS + " from dbo.app_Question";
    private static final String GET_QUESTION_BY_ID = "select " + QUESTION_ALIAS + " from dbo.app_Question where dbo.app_Question.Id=?";
    private static final String INSERT_QUESTION = "insert into dbo.app_Question (StoryId,Title,SortIndex,IsDisabled,BookmarkId,Icon) " +
            "output Inserted.id as " + QUESTION_PKID + ", Inserted.StoryId as " + QUESTION_FK_STORY_ID + ", Inserted.Title as "+ QUESTION_TITLE +", Inserted.SortIndex as "+ QUESTION_SORT_INDEX + ", Inserted.IsDisabled as " + QUESTION_IS_DISABLED + ", Inserted.BookmarkId as " + QUESTION_BOOKMARK_ID +  ", Inserted.Icon as " + QUESTION_ICON +
            " values (?,?,?,?,?,?)";
    private static final String UPDATE_QUESTION =
            "update dbo.app_Question " +
                    "set StoryId=?, Title=?,SortIndex=?,IsDisabled=?,BookmarkId=?,Icon=? " +
                    "where dbo.app_Question.Id=? ";
    private static final String DELETE_QUESTION = "delete from dbo.app_Question where dbo.app_Question.Id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> getAllQuestions() {
        return jdbcTemplate.query(GET_QUESTIONS, rs -> {
            List<Question> questionList = new ArrayList<>();
            while (rs.next()){
                questionList.add(getQuestionFromRs(rs));
            }
            return questionList;
        });
    }

    @Override
    public Question getQuestion(Integer questionId) {
        try {
            return jdbcTemplate.query(GET_QUESTION_BY_ID, new Object[]{questionId},
                    rs -> {if (rs.next()){ return getQuestionFromRs(rs);} return null;});
        } catch (EmptyResultDataAccessException e) {
            logger.error("Question not found ",e);
            return null;
        }
    }

    @Override
    public Question insertQuestion(Question question) {
        return jdbcTemplate.query(INSERT_QUESTION, new Object[]{question.getStoryId(),question.getTitle(),question.getSortIndex(),question.getDisabled(),question.getBookmarkId(), question.getIcon()},
                 rs -> {if (rs.next()){ return getQuestionFromRs(rs);} return null;});
    }

    @Override
    public Question updateQuestion(Integer questionId, Question question) {
         jdbcTemplate.update(UPDATE_QUESTION, question.getStoryId(),question.getTitle(),question.getSortIndex(),question.getDisabled(),question.getBookmarkId(), question.getIcon(), questionId);
        return getQuestion(questionId);
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        jdbcTemplate.update(DELETE_QUESTION, questionId);
    }

    @Override
    public Short getMaxQuestionSortIndex(Integer storyId){
        Short index = jdbcTemplate.queryForObject(MAX_INDEX, Short.class, storyId);
        return index != null ? index : 0;
    }

    protected static Question getQuestionFromRs(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt(QUESTION_PKID));
        question.setStoryId(rs.getInt(QUESTION_FK_STORY_ID));
        question.setTitle(rs.getString(QUESTION_TITLE));
        question.setSortIndex(rs.getShort(QUESTION_SORT_INDEX));
        question.setDisabled(rs.getBoolean(QUESTION_IS_DISABLED));
        question.setBookmarkId(rs.getLong(QUESTION_BOOKMARK_ID));
        question.setIcon(rs.getString(QUESTION_ICON));
        return question;
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Question");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }
}
