package us.deloitteinnovation.polaris.workflow.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.role.model.Role;
import us.deloitteinnovation.polaris.workflow.dao.IModuleDAO;
import us.deloitteinnovation.polaris.workflow.model.Module;
import us.deloitteinnovation.polaris.workflow.model.Question;
import us.deloitteinnovation.polaris.workflow.model.Story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static us.deloitteinnovation.polaris.workflow.dao.internal.QuestionDAOImpl.*;
import static us.deloitteinnovation.polaris.workflow.dao.internal.StoryDAOImpl.*;


/**
 * Created by rbentaarit on 8/12/2016.
 */
@Repository
public class ModuleDAOImpl extends AbstractDAO implements IModuleDAO {

    protected static final String MODULE_PKID = "modulePKID";
    protected static final String MODULE_TITLE = "moduleTitle";
    protected static final String MODULE_SORT_INDEX = "moduleSortIndex";
    protected static final String MODULE_IS_DISABLED = "moduleIsDisabled";
    protected static final String MODULE_IS_DEFAULT = "moduleIsDefault";
    private static final String ROLE_ID = "roleId";
    private static final  String ROLE_NAME = "roleName";

    private static final String MODULE_ALIAS =
            "dbo.app_Module.Id as " + MODULE_PKID +", " +
            "dbo.app_Module.Title as " + MODULE_TITLE +", " +
            "dbo.app_Module.SortIndex as " + MODULE_SORT_INDEX +", " +
            "dbo.app_Module.IsDisabled as " + MODULE_IS_DISABLED +", " +
            "dbo.app_Module.IsDefault as " + MODULE_IS_DEFAULT +", " +

            "dbo.app_Story.Id as " + STORY_PKID +", " +
            "dbo.app_Story.ModuleId as " + STORY_FK_MODULE_ID +", " +
            "dbo.app_Story.Title as " + STORY_TITLE +", " +
            "dbo.app_Story.SortIndex " + STORY_SORT_INDEX +", " +
            "dbo.app_Story.IsDisabled as " + STORY_IS_DISABLED +", " +

            "dbo.app_Question.Id as " + QUESTION_PKID +", " +
            "dbo.app_Question.StoryId as " + QUESTION_FK_STORY_ID +", " +
            "dbo.app_Question.Title as " + QUESTION_TITLE +", " +
            "dbo.app_Question.SortIndex as " + QUESTION_SORT_INDEX +", " +
            "dbo.app_Question.IsDisabled as " + QUESTION_IS_DISABLED +", " +
            "dbo.app_Question.BookmarkId as " + QUESTION_BOOKMARK_ID +", " +
            "dbo.app_Question.Icon as " + QUESTION_ICON + ", " +
            "dbo.app_Role.Id as " + ROLE_ID + ", " +
            "dbo.app_Role.Name as " + ROLE_NAME + " " ;



    private static final String MAX_INDEX = "SELECT MAX([SortIndex]) FROM [app_Module]";

    private static final String GET_WORKFLOW = "SELECT " + MODULE_ALIAS +
            "FROM dbo.app_Module LEFT JOIN dbo.app_Story ON dbo.app_Module.Id=dbo.app_Story.ModuleId " +
            "LEFT JOIN dbo.app_Question ON app_Story.Id=dbo.app_Question.StoryId " +
            "INNER JOIN app_Role_Module ON app_Module.Id = app_Role_Module.Module_Id " +
            "INNER JOIN app_Role ON app_Role.Id = app_Role_Module.Role_Id " +
            "ORDER BY " + MODULE_PKID;
    private static final String GET_MODULE_BY_ID = "SELECT " + MODULE_ALIAS +
            "FROM dbo.app_Module LEFT JOIN dbo.app_Story on dbo.app_Module.Id=dbo.app_Story.ModuleId " +
            "LEFT JOIN dbo.app_Question ON app_Story.Id=dbo.app_Question.StoryId " +
            "INNER JOIN app_Role_Module ON app_Module.Id = app_Role_Module.Module_Id " +
            "INNER JOIN app_Role ON app_Role.Id = app_Role_Module.Role_Id " +
            "where dbo.app_Module.Id = ? " +
            "ORDER BY " + QUESTION_PKID;
    private static final String INSERT_MODULE = "INSERT INTO dbo.app_Module (Title,SortIndex,IsDisabled,IsDefault) " +
            "OUTPUT Inserted.id AS "+ MODULE_PKID +", Inserted.Title AS "+ MODULE_TITLE +", Inserted.SortIndex AS "+ MODULE_SORT_INDEX + ", Inserted.IsDisabled AS " + MODULE_IS_DISABLED + ", Inserted.IsDefault AS " + MODULE_IS_DEFAULT + " " +
            "VALUES (?,?,?,?)";
    private static final String UPDATE_MODULE =
            "UPDATE dbo.app_Module " +
            "SET Title=?,SortIndex=?,IsDisabled=?,IsDefault=? " +
            "WHERE dbo.app_Module.Id=? ";
    private static final String DELETE_MODULE = "DELETE FROM dbo.app_Module WHERE dbo.app_Module.Id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ModuleDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Module> getAllModules(){
            return jdbcTemplate.query(GET_WORKFLOW, rs -> {
                Map<Integer, Module> moduleMap = new HashMap<>();
                Map<Integer, Story> storyMap = new HashMap<>();
                Map<Integer, Question> questionMap = new HashMap<>();
                Map<Integer, List<Role>> roleMap = new HashMap<>();
                while (rs.next()) {
                    int moduleId =  rs.getInt(MODULE_PKID);
                    if (!moduleMap.containsKey(moduleId)){
                        moduleMap.put(moduleId, getModuleFromRs(rs));
                    }
                    Role roleExtracted = getRoleFromRs(rs);
                    if (!roleMap.containsKey(moduleId))
                        roleMap.put(moduleId, new ArrayList<>(Collections.singletonList(roleExtracted)));
                    else
                        if (roleMap.get(moduleId).stream().noneMatch(role -> role.getName().equals(roleExtracted.getName()) && role.getId().equals(roleExtracted.getId())))
                            roleMap.get(moduleId).add(roleExtracted);

                    int storyId = rs.getInt(STORY_PKID);
                    if (!storyMap.containsKey(storyId)){
                        storyMap.put(storyId, getStoryFromRs(rs));
                    }
                    int questionId = rs.getInt(QUESTION_PKID);
                    if (!questionMap.containsKey(questionId)){
                        questionMap.put(questionId, getQuestionFromRs(rs));
                    }
                }
                storyMap = storyMapper(storyMap, questionMap);
                for (Map.Entry<Integer, Module> moduleIterator : moduleMap.entrySet()){
                    Module module = moduleIterator.getValue();
                    List<Story> storyList =
                            storyMap.entrySet().stream().filter(m -> m.getValue().getModuleId() == module.getId()).map(Map.Entry::getValue).collect(Collectors.toList());
                    storyList.sort(Comparator.comparingInt(Story::getId));
                    module.setStoryList(storyList);
                    module.setRoles(roleMap.get(module.getId()));
                    moduleMap.put(moduleIterator.getKey(),module);
                }
                List<Module> modules = new ArrayList<>(moduleMap.values());
                modules.sort(Comparator.comparingInt(Module::getSortIndex));
                return modules;
            });
    }

    @Override
    public Module getModule(Integer moduleId) {
        try {
            return jdbcTemplate.query(GET_MODULE_BY_ID, new Object[]{moduleId}, new ModuleRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Module not found ", e);
            return null;
        }
    }

    @Override
    public Module insertModule(Module module){
        return jdbcTemplate.query(INSERT_MODULE, new Object[]{module.getTitle(),module.getSortIndex(),module.getDisabled(),module.getDefault()},
                rs -> rs.next() ? getModuleFromRs(rs): null);
    }

    @Override
    public Integer insertModuleReturnId(Module module) {
        return jdbcTemplate.query(INSERT_MODULE, new Object[]{module.getTitle(),module.getSortIndex(),module.getDisabled(),module.getDefault()},
                rs -> rs.next() ? rs.getInt(MODULE_PKID) : 0);
    }

    @Override
    public Module updateModule(Integer moduleId, Module module){
        jdbcTemplate.update(UPDATE_MODULE, module.getTitle(),module.getSortIndex(),module.getDisabled(),module.getDefault(), moduleId);
        return getModule(moduleId);
    }

    @Override
    public void deleteModule(Integer moduleId){
        jdbcTemplate.update(DELETE_MODULE, moduleId);
    }

    @Override
    public Short getMaxModuleSortIndex(){
        Short index = jdbcTemplate.queryForObject(MAX_INDEX, Short.class);
        return index != null ? index : 0;
    }

    private static Module getModuleFromRs(ResultSet rs) throws SQLException {
        Module module = new Module();
        module.setId(rs.getInt(MODULE_PKID));
        module.setTitle(rs.getString(MODULE_TITLE));
        module.setSortIndex(rs.getShort(MODULE_SORT_INDEX));
        module.setDefault(rs.getBoolean(MODULE_IS_DEFAULT));
        module.setDisabled(rs.getBoolean(MODULE_IS_DISABLED));
        return module;
    }

    private static Role getRoleFromRs(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt(ROLE_ID));
        role.setName(rs.getString(ROLE_NAME));
        return role;
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Module");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }

    private class ModuleRowMapper implements  ResultSetExtractor<Module>{

    @Override
    public Module extractData(ResultSet rs) throws SQLException {
            Map<Integer, Story> storyMap = new HashMap<>();
            Map<Integer, Question> questionMap = new HashMap<>();
            List<Role> roles = new ArrayList<>();
            Module module = null;
            while (rs.next()) {
                if (module == null){
                    module = getModuleFromRs(rs);
                }
                int storyId = rs.getInt(STORY_PKID);
                if (storyId != 0 && !storyMap.containsKey(storyId)){
                    storyMap.put(storyId, getStoryFromRs(rs));
                }
                int questionId = rs.getInt(QUESTION_PKID);
                if (questionId != 0 && !questionMap.containsKey(questionId)){
                    questionMap.put(questionId, getQuestionFromRs(rs));
                }
                Role roleExtracted = getRoleFromRs(rs);
                if (roles.stream().noneMatch(role -> (role.getName().equals(roleExtracted.getName()) && role.getId().equals(roleExtracted.getId()))))
                    roles.add(getRoleFromRs(rs));
            }
            storyMap = storyMapper(storyMap, questionMap);
            List<Story> storyList = new ArrayList<>(storyMap.values());
            storyList.sort(Comparator.comparingInt(Story::getSortIndex));
            if (module != null){
                module.setStoryList(storyList);
                module.setRoles(roles);
            }
            return module;
        }
    }

}


