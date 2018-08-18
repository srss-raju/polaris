package us.deloitteinnovation.polaris.filter.dao;

import us.deloitteinnovation.polaris.filter.model.ChartFilter;

import java.util.List;

/**
 * Created by rbentaarit on 2/8/2017.
 */
@FunctionalInterface
public interface IChartFilterDAO {
    List<ChartFilter> getChartFilters();
}
