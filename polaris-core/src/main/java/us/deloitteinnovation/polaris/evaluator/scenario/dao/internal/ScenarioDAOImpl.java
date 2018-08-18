package us.deloitteinnovation.polaris.evaluator.scenario.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.evaluator.scenario.dao.IScenarioDAO;
import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;
import us.deloitteinnovation.polaris.evaluator.scenario.model.ScenarioEvent;

import java.util.*;

/**
 * Created by rbentaarit on 6/27/2017.
 */
@Repository
public class ScenarioDAOImpl extends AbstractDAO implements IScenarioDAO {

    public static final String PARAM_NAME = "name";
    public static final String GROSS_REVENUE = "grossRevenue";
    public static final String IS_PLAN = "isPlan";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String AVAILABLE_FUNDING = "availableFunding";
    public static final String  AUTHOR = "author";
    public static final String PLAN_ID = "planId";
    public static final String ID = "id";
    public static final String SCENARIO_ID = "scenarioId";
    public static final String EVENT_ID = "eventId";

    //queries
    private static final String GET_PREPARED_STATEMENT_QUERY_BASE = "SELECT " +
            "[ID] as id "+
            ",[Name] as name "+
            ",[Start_Date] as startDate "+
            ",[End_Date] as endDate "+
            ",[IsPlan] as isPlan  "+
            ",[Author] as author "+
            ",[Plan_ID] as planId "+
            ",[Gross_Revenue]  as grossRevenue"+
            ",[Available_Funding] as availableFunding "+
            "FROM [app_Scenario] ";

    private static final String GET_PREPARED_STATEMENT_QUERY_BY_NAME = GET_PREPARED_STATEMENT_QUERY_BASE + " WHERE [Name] = :scenarioName " +
            "and [Author] = :author";

    private static final String GET_PREPARED_STATEMENT_QUERY_BY_ID = GET_PREPARED_STATEMENT_QUERY_BASE + " WHERE ID = :id " +
            "and [Author] = :author " +
            "and  IsPlan = 0";

    private static final String GET_PREPARED_STATEMENT_QUERY_BY_PLAN_DETAIL_ID = GET_PREPARED_STATEMENT_QUERY_BASE + " WHERE ID = :id and IsPlan = 1";

    private static final String GET_PREPARED_STATEMENT_QUERY_BY_PLAN_ID = GET_PREPARED_STATEMENT_QUERY_BASE + " WHERE Plan_ID = :id and  IsPlan = 0 and [Author] = :author";

    private static final String GET_LIST_PLAN = GET_PREPARED_STATEMENT_QUERY_BASE + " WHERE IsPlan = 1";

    private static final String GET_LIST_PREPARED_STATEMENT_QUERY = GET_PREPARED_STATEMENT_QUERY_BASE + " WHERE [Author] = :author";

    private static final String SAVE_SCENARIO_EVENTS_MAPPING = "INSERT INTO [app_Scenario_Event] " +
            "([Scenario_ID], [Event_ID]) " +
            " VALUES " +
            "(:scenarioId, :eventId) ";

    private static final String SAVE_PREPARED_STATEMENT_QUERY = "INSERT INTO [app_Scenario] ( "+
            " [Name], "+
            "[Start_Date], "+
            "[End_Date], "+
            "[Author], "+
            "[Plan_ID], "+
            "[Gross_Revenue], "+
            "[Available_Funding] "+
            ")  OUTPUT Inserted.id  VALUES ( "+
            ":name, "+
            ":startDate, "+
            ":endDate, "+
            ":author, "+
            ":planId, "+
            ":grossRevenue, "+
            ":availableFunding)  ";

    private static final String UPDATE_PREPARED_STATEMENT_QUERY = "UPDATE [app_Scenario] " +
            "SET [Name] = :name, " +
            "[Start_Date] = :startDate, " +
            "[End_Date] = :endDate, " +
            "[Author] = :author, " +
            "[Plan_ID] = :planId, " +
            "[Gross_Revenue] = :grossRevenue, " +
            "[Available_Funding] = :availableFunding " +
            "WHERE id = :id ";
    public static final String DELETE_FROM_SCENARIO_EVENT_BY_SCENARIO_ID = "delete from [app_Scenario_Event] where [Scenario_ID] = :scenarioId";
    public static final String SCENARIO_NAME = "scenarioName";

    private static final String GET_EVENTS_BY_SCENARIO = "select Scenario_ID as scenarioId, Event_ID as eventId from dbo.app_Scenario_Event where Scenario_ID = :scenarioId order by Event_ID";

    @Autowired
    private AuditorAware<String> userAuditor;

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Scenario");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("ID");
    }

    @Override
    public Integer save(Scenario scenario) {
        Map params = getMapSqlParameterSource(scenario);
        return getNamedParameterJdbcTemplate().queryForObject(SAVE_PREPARED_STATEMENT_QUERY, params, Integer.class);
    }

    @Override
    public Scenario getScenarioByName(String scenarioName) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put(SCENARIO_NAME, scenarioName);
            map.put(AUTHOR, userAuditor.getCurrentAuditor());
            List<Scenario> query = getNamedParameterJdbcTemplate().query(GET_PREPARED_STATEMENT_QUERY_BY_NAME, map, new BeanPropertyRowMapper<>(Scenario.class));
            return query.size() > 0 ? query.get(0) : null;
        } catch (Exception e){
            return null;
        }

    }

    @Override
    public Integer update(Scenario scenario) {
        Map params = getMapSqlParameterSource(scenario);
        return getNamedParameterJdbcTemplate().update(UPDATE_PREPARED_STATEMENT_QUERY, params);
    }


    @Override
    public Integer saveAs(Scenario scenario) {
        return save(scenario);
    }

    @Override
    public Scenario getScenario(Integer id) {
        return getPlanDetailsOrScenario(id, false);
    }

    @Override
    public Scenario getPlanDetails(Integer id) {
        return getPlanDetailsOrScenario(id, true);
    }

    private Scenario getPlanDetailsOrScenario(Integer id, boolean isPlan) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put(ID, id);
            map.put(AUTHOR, userAuditor.getCurrentAuditor());
            if(isPlan) {
                return getNamedParameterJdbcTemplate().queryForObject(GET_PREPARED_STATEMENT_QUERY_BY_PLAN_DETAIL_ID, map, new BeanPropertyRowMapper<>(Scenario.class));
            } else {
                return getNamedParameterJdbcTemplate().queryForObject(GET_PREPARED_STATEMENT_QUERY_BY_ID, map, new BeanPropertyRowMapper<>(Scenario.class));
            }
        } catch(EmptyResultDataAccessException exp){
            return null;
        }
    }

    private Map<String, Object> getMapSqlParameterSource(Scenario scenario) {
        Map<String, Object> map = new HashMap<>();
        map.put(PARAM_NAME, scenario.getName());
        map.put(GROSS_REVENUE, scenario.getGrossRevenue());
        map.put(IS_PLAN, scenario.getPlan());
        map.put(START_DATE, scenario.getStartDate());
        map.put(END_DATE, scenario.getEndDate());
        map.put(AVAILABLE_FUNDING, scenario.getAvailableFunding());
        map.put(AUTHOR, userAuditor.getCurrentAuditor());
        map.put(PLAN_ID, scenario.getPlanId());
        map.put(ID, scenario.getId());
        return map;
    }

    @Override
    public List<Scenario> getScenario() {
        Map<String, Object> map = new HashMap<>();
        map.put(AUTHOR, userAuditor.getCurrentAuditor());
        return getNamedParameterJdbcTemplate().query(GET_LIST_PREPARED_STATEMENT_QUERY, map, new BeanPropertyRowMapper<>(Scenario.class));
    }

    @Override
    public void saveEventsMapping(Scenario scenario) {
        int size = scenario.getEventIds().size();
        List<Map<String, Object>> batchValues = new ArrayList<>(size);
        scenario.getEventIds().forEach(eventId -> batchValues.add(new MapSqlParameterSource(SCENARIO_ID, scenario.getId()).addValue(EVENT_ID, eventId).getValues()));
        getNamedParameterJdbcTemplate().batchUpdate(SAVE_SCENARIO_EVENTS_MAPPING, batchValues.toArray(new Map[size]));
    }

    @Override
    public void deleteEventsMappingByScenarioId(Integer scenarioId) {
        getNamedParameterJdbcTemplate().update(DELETE_FROM_SCENARIO_EVENT_BY_SCENARIO_ID, Collections.singletonMap(SCENARIO_ID, scenarioId));
    }

    @Override
    public List<Scenario> getPlans() {
        return getNamedParameterJdbcTemplate().query(GET_LIST_PLAN, new BeanPropertyRowMapper<>(Scenario.class));
    }

    @Override
    public List<Scenario> getScenariosByPlan(Integer planId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", planId);
            map.put(AUTHOR, userAuditor.getCurrentAuditor());
            return getNamedParameterJdbcTemplate().query(GET_PREPARED_STATEMENT_QUERY_BY_PLAN_ID, map, new BeanPropertyRowMapper<>(Scenario.class));
        } catch(EmptyResultDataAccessException exp){
            return null;
        }

    }

    @Override
    public List<ScenarioEvent> getAllEventsForScenarioId(Integer scenarioId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("scenarioId", scenarioId);
            return getNamedParameterJdbcTemplate().query(GET_EVENTS_BY_SCENARIO, map, new BeanPropertyRowMapper<>(ScenarioEvent.class));
        } catch(EmptyResultDataAccessException exp){
            return null;
        }
    }

}
