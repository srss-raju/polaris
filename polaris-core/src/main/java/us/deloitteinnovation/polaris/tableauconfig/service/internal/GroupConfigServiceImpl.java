package us.deloitteinnovation.polaris.tableauconfig.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.tableauconfig.dao.IGroupConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Group;
import us.deloitteinnovation.polaris.tableauconfig.service.IFilterConfigService;
import us.deloitteinnovation.polaris.tableauconfig.service.IGroupConfigService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Service
public class GroupConfigServiceImpl implements IGroupConfigService {

    private final IGroupConfigDAO groupConfigDAO;
    private final IFilterConfigService filterConfigService;

    @Autowired
    public GroupConfigServiceImpl(IGroupConfigDAO groupConfigDAO, IFilterConfigService filterConfigService) {
        this.groupConfigDAO = groupConfigDAO;
        this.filterConfigService = filterConfigService;
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups =  groupConfigDAO.getAllGroups();
        groups.forEach(group -> group.setFilters(filterConfigService.getFilterByGroupConfig(group.getId())));
        return groups;
    }

    @Override
    public Group getGroupById(Integer groupId) {
        Group group = groupConfigDAO.getGroupById(groupId);
        group.setFilters(filterConfigService.getFilterByGroupConfig(groupId));
        return group;
    }

    @Override
    public List<Group> getGroupBySheetConfig(Integer sheetConfigId) {
        List<Group> groups =  groupConfigDAO.getGroupBySheetConfig(sheetConfigId);
        groups.forEach(group -> group.setFilters(filterConfigService.getFilterByGroupConfig(group.getId())));
        return groups;
    }

    @Transactional
    @Override
    public Group insertGroup(Group group) {
        Integer groupId = groupConfigDAO.insertGroup(group);
        if (group.getFilters() != null && !group.getFilters().isEmpty()){
            group.getFilters().forEach(filter -> filter.setGroupId(groupId));
            filterConfigService.insertFilters(group.getFilters());
        }
        return getGroupById(groupId);
    }
}
