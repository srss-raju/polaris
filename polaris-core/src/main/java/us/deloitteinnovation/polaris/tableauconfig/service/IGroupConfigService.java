package us.deloitteinnovation.polaris.tableauconfig.service;

import us.deloitteinnovation.polaris.tableauconfig.model.Group;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface IGroupConfigService {
    List<Group> getAllGroups();

    Group getGroupById(Integer groupId);

    List<Group> getGroupBySheetConfig(Integer sheetConfigId);

    Group insertGroup(Group group);
}
