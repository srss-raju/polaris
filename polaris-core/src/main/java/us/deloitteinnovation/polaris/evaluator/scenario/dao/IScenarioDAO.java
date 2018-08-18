package us.deloitteinnovation.polaris.evaluator.scenario.dao;

import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;
import us.deloitteinnovation.polaris.evaluator.scenario.model.ScenarioEvent;

import java.util.List;

/**
 * Created by rbentaarit on 6/27/2017.
 */
public interface IScenarioDAO {
    Integer save(Scenario scenario);
    Integer update(Scenario scenario);
    Integer saveAs(Scenario scenario);
    Scenario getScenario(Integer id);
    Scenario getPlanDetails(Integer id);
    List<Scenario> getScenario();
    void saveEventsMapping(Scenario scenario);
    void deleteEventsMappingByScenarioId(Integer scenarioId);

    List<Scenario> getPlans();

    List<Scenario> getScenariosByPlan(Integer planId);

    Scenario getScenarioByName(String scenarioName);

    List<ScenarioEvent> getAllEventsForScenarioId(Integer scenarioId);
}
