package us.deloitteinnovation.polaris.evaluator.calculation.service;

import us.deloitteinnovation.polaris.evaluator.calculation.model.Metric;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;

/**
 * Created by rbentaarit on 6/13/2017.
 */
public interface ICalculationService {
   Metric calculateEvent(Integer EventId);
   Metric calculateProduct(Integer simProductId);
   Metric calculateScenario(Integer ScenarioId);
    Event simulateEvent(Event event);

    Scenario simulateScenario(Scenario scenario);
}
