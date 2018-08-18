package us.deloitteinnovation.polaris.tableauconfig.dao;

import us.deloitteinnovation.polaris.tableauconfig.model.ClientConfig;
import us.deloitteinnovation.polaris.tableauconfig.model.Filter;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface IFilterConfigDAO {
    List<Filter> getAllFilters();
    Filter getFilterById(Integer filterId);
    List<Filter> getFilterByGroupConfig(Integer groupConfigId);
    Integer insertFilter(Filter filter);
    int[] insertFilters(List<Filter> filters);
    List<ClientConfig> getClientConfigs();
}
