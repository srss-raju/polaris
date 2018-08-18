package us.deloitteinnovation.polaris.evaluator.scenario.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.deloitteinnovation.polaris.evaluator.event.dao.IEventDAO;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.scenario.dao.IScenarioDAO;
import us.deloitteinnovation.polaris.evaluator.scenario.error.ScenarioException;
import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;
import us.deloitteinnovation.polaris.evaluator.scenario.model.ScenarioEvent;
import us.deloitteinnovation.polaris.evaluator.scenario.service.IScenarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rbentaarit on 6/27/2017.
 */
@Service
public class ScenarioServiceImpl implements IScenarioService {
    private final IScenarioDAO scenarioDAO;
    private final IEventDAO eventDAO;

    @Autowired
    public ScenarioServiceImpl(IScenarioDAO scenarioDAO, IEventDAO eventDAO) {
        this.scenarioDAO = scenarioDAO;
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public Scenario saveScenario(Scenario sourceScenario) {

        //Get the plan details.
        Scenario planDetails = this.getPlanDetails(sourceScenario.getPlanId());
        planDetails.setId(null);
        planDetails.setAvailableFunding(sourceScenario.getAvailableFunding());
        planDetails.setGrossRevenue(sourceScenario.getGrossRevenue());
        planDetails.setName(sourceScenario.getName());
        planDetails.setPlan(false);
        planDetails.setPlanId(sourceScenario.getPlanId());
        planDetails.setEventIds(sourceScenario.getEventIds());

        //check scenario name for uniquness.
        if(scenarioDAO.getScenarioByName(planDetails.getName()) != null) {
            throw new ScenarioException("Scenario Name Already Exists!!!");
        }

        List<Integer> eventIds = planDetails.getEventIds();
        if(null != eventIds && eventIds.size() > 0) {
            Set<Integer> evtIds = new HashSet<>(eventIds);
            if(eventIds.size() != evtIds.size()) {
                throw  new ScenarioException("Found Duplicate Event Added The Scenario");
            }
            final Date startDate = planDetails.getStartDate();
            final Date endDate = planDetails.getEndDate();
            //check if events start and end dates are with in the scenario start and end dates
            eventIds.stream().forEach(eventId -> {
                Event eventDetails = eventDAO.getEventById(eventId);
                if(!(eventDetails.getStartDate().after(startDate)
                        && eventDetails.getEndDate().before(endDate))) {
                    throw new ScenarioException("The Selected Events Date Range Are Not With In The Scenario Date Range.");
                }
            });
        }
        //save Scenario
        Integer id = scenarioDAO.save(planDetails);
        planDetails.setId(id);
        //save Events-Scenario
        if(null != eventIds && eventIds.size() > 0) {
            scenarioDAO.saveEventsMapping(planDetails);
        }
        return planDetails;
    }

    @Override
    public Scenario getScenario(Integer id) {
        Scenario scenario = scenarioDAO.getScenario(id);
        List<ScenarioEvent> allEventsForScenarioId = scenarioDAO.getAllEventsForScenarioId(id);
        scenario.setEventIds(new ArrayList<>());
        allEventsForScenarioId.stream().forEach(scenarioEvent -> scenario.getEventIds().add(scenarioEvent.getEventId()));
        return scenario;
    }

    @Override
    public Scenario getPlanDetails(Integer id) {
        return scenarioDAO.getPlanDetails(id);
    }

    @Override
    public List<Scenario> getPlans() {
        return scenarioDAO.getPlans();
    }

    @Override
    public List<Scenario> getScenariosByPlan(Integer planId) {
        return scenarioDAO.getScenariosByPlan(planId);
    }

    @Override
    public List<Scenario> getScenario() {
        return scenarioDAO.getScenario();
    }

    @Override
    @Transactional
    public Scenario updateScenario(Scenario scenario) {
        Scenario saveScenarioDetails = scenarioDAO.getScenario(scenario.getId());

        if(null != scenario.getEventIds() && scenario.getEventIds().size() > 0) {
            List<Integer> eventIds =  new ArrayList<>();

            List<ScenarioEvent> allEventsForScenarioId = scenarioDAO.getAllEventsForScenarioId(scenario.getId());
            if(null != allEventsForScenarioId && allEventsForScenarioId.size() > 0) {
                allEventsForScenarioId.stream().forEach(scenarioEventMap -> eventIds.add(scenarioEventMap.getEventId()));
            }

            eventIds.addAll(scenario.getEventIds());

            Set<Integer> evtIds = new HashSet<>(eventIds);
            if(eventIds.size() != evtIds.size()) {
                throw  new ScenarioException("Found Duplicate Event Added The Scenario");
            }

            List<Integer> events = scenario.getEventIds();
            //save Scenario
            scenarioDAO.update(scenario);

            //save Events-Scenario
            if(null != events && events.size() > 0) {
                scenarioDAO.saveEventsMapping(scenario);
            }
        }
        return scenario;
    }
}
