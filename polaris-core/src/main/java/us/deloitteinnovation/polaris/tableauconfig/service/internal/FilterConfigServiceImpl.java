package us.deloitteinnovation.polaris.tableauconfig.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.tableauconfig.dao.IFilterConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.ClientConfig;
import us.deloitteinnovation.polaris.tableauconfig.model.Filter;
import us.deloitteinnovation.polaris.tableauconfig.service.IFilterConfigService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Service
public class FilterConfigServiceImpl implements IFilterConfigService {

    private final IFilterConfigDAO filterConfigDAO;

    @Autowired
    public FilterConfigServiceImpl(IFilterConfigDAO filterConfigDAO) {
        this.filterConfigDAO = filterConfigDAO;
    }

    @Override
    public List<Filter> getAllFilters() {
        return filterConfigDAO.getAllFilters();
    }

    @Override
    public Filter getFilterById(Integer filterId) {
        return filterConfigDAO.getFilterById(filterId);
    }

    @Override
    public List<Filter> getFilterByGroupConfig(Integer groupConfigId) {
        return filterConfigDAO.getFilterByGroupConfig(groupConfigId);
    }

    @Override
    public Filter insertFilter(Filter filter) {
        Integer filterId = filterConfigDAO.insertFilter(filter);
        return filterConfigDAO.getFilterById(filterId);
    }

    @Override
    public int[] insertFilters(List<Filter> filters) {
        return filterConfigDAO.insertFilters(filters);
    }

    @Override
    public Map<String,String> getClientConfigs(){
        List<ClientConfig> clientConfigList = filterConfigDAO.getClientConfigs();
        //replace _ present in column name with space
        clientConfigList.forEach(c -> c.setColumnName(c.getColumnName().replace("_"," ")));
        Map<String,String> clientConfigMap =  clientConfigList.stream().collect(Collectors.toMap(ClientConfig::getColumnName,ClientConfig::getClientLabel,(u, v) -> {
                    throw new IllegalStateException(String.format("Client Config Manipulation Duplicate key %s", u)); }, LinkedHashMap::new ));
        return clientConfigMap;
    }

}
