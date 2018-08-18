package us.deloitteinnovation.polaris.tableauconfig.dao.internal;

import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.tableauconfig.dao.IGroupConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Repository
public class GroupConfigDAOImpl extends AbstractDAO implements IGroupConfigDAO {
    private final static String GET_ALL_GROUPS = "SELECT [Id],[name],[sheet_config_Id] FROM [dbo].[app_Group_Config]";
    private final static String GET_GROUP_BY_ID = "SELECT [Id],[name],[sheet_config_Id] FROM [dbo].[app_Group_Config] WHERE [Id]=:groupId";
    private final static String GET_GROUP_BY_SHEET_CONFIG = "SELECT [Id],[name],[sheet_config_Id] FROM [dbo].[app_Group_Config] WHERE [sheet_config_Id]=:sheetId";
    private final static String INSERT_GROUP = "INSERT INTO [dbo].[app_Group_Config] ([name],[sheet_config_Id]) OUTPUT Inserted.id VALUES (:name, :sheetId)";

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Group_Config");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }

    @Override
    public List<Group> getAllGroups() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_GROUPS, rs -> {
            List<Group> groups = new ArrayList<>();
            while (rs.next()){
                groups.add(extractGroupFromResultSet(rs));
            }
            return groups;
        });
    }

    @Override
    public Group getGroupById(Integer groupId) {
        return getNamedParameterJdbcTemplate().query(GET_GROUP_BY_ID, Collections.singletonMap("groupId", groupId), rs -> rs.next() ? extractGroupFromResultSet(rs) : null);
    }

    @Override
    public List<Group> getGroupBySheetConfig(Integer sheetConfigId) {
        return getNamedParameterJdbcTemplate().query(GET_GROUP_BY_SHEET_CONFIG, Collections.singletonMap("sheetId", sheetConfigId), rs -> {
            List<Group> groups = new ArrayList<>();
            while (rs.next()){
                groups.add(extractGroupFromResultSet(rs));
            }
            return groups;
        });
    }

    @Override
    public Integer insertGroup(Group group) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", group.getName());
        paramMap.put("sheetId", group.getSheetId());
        return getNamedParameterJdbcTemplate().queryForObject(INSERT_GROUP, paramMap, Integer.class);
    }

    private Group extractGroupFromResultSet(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("Id"));
        group.setName(rs.getString("name"));
        group.setSheetId(rs.getInt("sheet_config_Id"));
        return group;
    }
}
