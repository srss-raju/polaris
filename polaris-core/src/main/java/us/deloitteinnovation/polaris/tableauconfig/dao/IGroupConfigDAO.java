package us.deloitteinnovation.polaris.tableauconfig.dao;

import us.deloitteinnovation.polaris.tableauconfig.model.Group;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface IGroupConfigDAO {
    List<Group> getAllGroups();

    Group getGroupById(Integer groupId);

    List<Group> getGroupBySheetConfig(Integer sheetConfigId);

    Integer insertGroup(Group group);
}
