package us.deloitteinnovation.polaris.evaluator.event.dao.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.common.util.Constant;
import us.deloitteinnovation.polaris.evaluator.event.dao.IEventDAO;
import us.deloitteinnovation.polaris.evaluator.event.error.EmptyEventException;
import us.deloitteinnovation.polaris.evaluator.event.error.EventException;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.event.model.EventTactic;
import us.deloitteinnovation.polaris.evaluator.event.model.EventType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rbentaarit on 5/31/2017.
 */
@Repository
public class EventDAOImpl extends AbstractDAO implements IEventDAO {

    private static final String GET_TACTICS = "SELECT [Promo_Tactic] FROM [polaris-gold].[dbo].[svw_Promo_Tactic]";
    private static final String GET_TYPES = "SELECT [Promo_Type]  FROM [polaris-gold].[dbo].[svw_Promo_Type]";
    private static final String INSERT_NEW_EVENT = "INSERT INTO [dbo].[app_Event] ([Name], [Start_Date], [End_Date], [Type], [Tactic], [Campaign_Name], [Campaign_Holiday], [Author], [Promo_ID]) " +
            " OUTPUT Inserted.id  VALUES   (:name, :startDate, :endDate, :type, :tactic, :campaignName, :campaignHoliday, :author, :promoId)";
    private static final String GET_EVENT_DETAILS = "SELECT [ID], [Name], [Start_Date], [End_Date], [Type], [Tactic], [Campaign_Name], [Campaign_Holiday], [Promo_ID], [Author] FROM [dbo].[app_Event] ";
    private static final String GET_EVENT_BY_ID = GET_EVENT_DETAILS + " WHERE [ID] = :eventId";
    private static final String GET_EVENT_BY_EVENT_NAME = GET_EVENT_DETAILS + " WHERE [Name] = :eventName and [Author] = :author";
    private static final String IS_UPDATABLE = "Select Res from ( " +
            " SELECT [IsProvided],   CASE WHEN Count ([ID]) <= 1 and ([IsProvided] = 0) THEN 1 ELSE 0 END as Res " +
            " FROM [dbo].[app_Event]" +
            " LEFT JOIN [dbo].[app_Scenario_Event] on [dbo].[app_Scenario_Event].[Event_ID] = [ID]" +
            " WHERE  [ID] = :eventId" +
            " group by [IsProvided]) a";
    private static final String UPDATE_EVENT =  "UPDATE [dbo].[app_Event]" +
            "SET [Name]= :name , [Start_Date]=:startDate, [End_Date]=:endDate, [Type]=:type, [Tactic]=:tactic, [Campaign_Name]=:campaignName, [Campaign_Holiday]=:campaignHoliday " +
            "WHERE [dbo].[app_Event].[ID]=:id ";
    private static final String DELETE_EVENT = "DELETE FROM [dbo].[app_Event] WHERE [dbo].[app_Event].[ID]=:eventId AND [dbo].[app_Event].[Author]=:author";
    private static final String ALL_EVENTS_WITH_PAGINATION = "with event_list as (select row_number() over(order by Name, Id desc) as rownumber , " +
            "ID, " +
            "Name, " +
            "Start_Date, " +
            "End_Date, " +
            "Type, " +
            "Tactic, " +
            "Campaign_Name, " +
            "Campaign_Holiday, " +
            "IsProvided, " +
            "Customer_ID, " +
            "Promo_ID " +
            "from app_Event where author = :author) " +
            "select * from  event_list " +
            "where rownumber >= :startRowId and rownumber <= :endRowId";

    private static final String ALL_EVENTS_WITHOUT_PAGINATION = "select " +
            "ID, " +
            "Name, " +
            "Start_Date, " +
            "End_Date, " +
            "Type, " +
            "Tactic, " +
            "Campaign_Name, " +
            "Campaign_Holiday, " +
            "IsProvided, " +
            "Customer_ID, " +
            "Promo_ID " +
            "from app_Event where author = :author ";

    @Autowired
    private AuditorAware<String> userAuditor;

    @Override
    public EventTactic getEventTactics() {
       return new EventTactic(getNamedParameterJdbcTemplate().queryForList(GET_TACTICS, Collections.emptyMap(), String.class));
    }

    @Override
    public EventType getEventTypes() {
        return new EventType(getNamedParameterJdbcTemplate().queryForList(GET_TYPES, Collections.emptyMap(), String.class));
    }

    @Override
    public Integer insertEvent(Event event) {
        Map<String, Object> params = new ObjectMapper().convertValue(event, Map.class);
        params.put("author", userAuditor.getCurrentAuditor());
       return getNamedParameterJdbcTemplate().queryForObject(INSERT_NEW_EVENT,  params,Integer.class);
    }

    @Override
    public Event getEventById(Integer eventId) {
        try {
            return getNamedParameterJdbcTemplate().queryForObject(GET_EVENT_BY_ID, Collections.singletonMap("eventId", eventId), new BeanPropertyRowMapper<>(Event.class));
        } catch (EmptyResultDataAccessException e){
            throw new EmptyEventException("Id : " + eventId + " not found", e);
        }
    }


    @Override
    public Event getEventByNameAndAuthor(String eventName) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("eventName", eventName);
            params.put("author", userAuditor.getCurrentAuditor());
            return getNamedParameterJdbcTemplate().queryForObject(GET_EVENT_BY_EVENT_NAME, params, new BeanPropertyRowMapper<>(Event.class));
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Boolean isEventUpdatable(Integer eventId){
        return getNamedParameterJdbcTemplate().queryForObject(IS_UPDATABLE, Collections.singletonMap("eventId", eventId), Boolean.class);
    }

    @Override
    public void updateEvent(Event event){
        Map<String, Object> params = new ObjectMapper().convertValue(event, Map.class);
        params.put("author", userAuditor.getCurrentAuditor());
        getNamedParameterJdbcTemplate().update(UPDATE_EVENT, params);
    }

    @Override
    public void updateEvents(List<Event> events){
        getNamedParameterJdbcTemplate().batchUpdate(UPDATE_EVENT, SqlParameterSourceUtils.createBatch(events.toArray()));
    }

    @Override
    public Integer deleteEvent(Integer eventId){
        Map<String, Object> params = Collections.singletonMap("eventId", eventId);
        params.put("author", userAuditor.getCurrentAuditor());
       return getNamedParameterJdbcTemplate().update(DELETE_EVENT, params);
    }

    @Override
    protected Optional<String> getTable() {
        return Optional.of("Event");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("ID");
    }

    @Override
    public List<Event> allEvents(Integer offset, Integer size) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        if(size == 0 || offset == 0) {
            params.addValue("author", userAuditor.getCurrentAuditor());
            return getNamedParameterJdbcTemplate().query(ALL_EVENTS_WITHOUT_PAGINATION, params, new BeanPropertyRowMapper<>(Event.class));

        } else {
            int _offSet = (offset < 1) ? Constant.DEFAULT_OFFSET : offset;
            int _size = (size < 1) ? Constant.DEFAULT_SIZE : size;

            params.addValue("author", userAuditor.getCurrentAuditor());
            params.addValue("startRowId", _offSet);
            params.addValue("endRowId", _size);
            return getNamedParameterJdbcTemplate().query(ALL_EVENTS_WITH_PAGINATION, params, new BeanPropertyRowMapper<>(Event.class));
        }
    }

}
