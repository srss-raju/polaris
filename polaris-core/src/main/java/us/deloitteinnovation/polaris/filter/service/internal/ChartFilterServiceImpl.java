package us.deloitteinnovation.polaris.filter.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.filter.dao.IChartFilterDAO;
import us.deloitteinnovation.polaris.filter.model.ChartFilter;
import us.deloitteinnovation.polaris.filter.service.IChartFilterService;

import java.util.List;

/**
 * Created by rbentaarit on 2/8/2017.
 */

@Service
public class ChartFilterServiceImpl implements IChartFilterService{

    @Autowired
    private IChartFilterDAO chartFilterDAO;

    @Override
    public List<ChartFilter> getChartFilter() {
        return chartFilterDAO.getChartFilters();
    }
}