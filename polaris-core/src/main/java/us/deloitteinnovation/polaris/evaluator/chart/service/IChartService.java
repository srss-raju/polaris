package us.deloitteinnovation.polaris.evaluator.chart.service;

import us.deloitteinnovation.polaris.evaluator.chart.model.Chart;

/**
 * Created by rbentaarit on 7/6/2017.
 */
public interface IChartService {
    Chart calculateChart(Integer scenarioId, Boolean isPlan);

}
