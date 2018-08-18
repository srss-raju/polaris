package us.deloitteinnovation.polaris.tableauconfig.service;

import us.deloitteinnovation.polaris.tableauconfig.model.Filter;

import java.util.List;
import java.util.Map;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface IFilterConfigService {
    List<Filter> getAllFilters();
    Filter getFilterById(Integer filterId);
    List<Filter> getFilterByGroupConfig(Integer groupConfigId);
    Filter insertFilter(Filter filter);
    int[] insertFilters(List<Filter> filters);
    Map<String,String> getClientConfigs();
}
