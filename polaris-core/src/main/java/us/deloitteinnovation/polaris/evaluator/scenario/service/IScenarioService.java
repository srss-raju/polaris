package us.deloitteinnovation.polaris.evaluator.scenario.service;

import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;

import java.util.List;

/**
 * Created by rbentaarit on 6/27/2017.
 */
public interface IScenarioService {
    Scenario saveScenario(Scenario scenario);
    Scenario getScenario(Integer id);
    List<Scenario> getScenario();
    Scenario updateScenario(Scenario scenario);
    Scenario getPlanDetails(Integer id);
    List<Scenario> getPlans();

    List<Scenario> getScenariosByPlan(Integer planId);
}
