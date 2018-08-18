package us.deloitteinnovation.polaris.evaluator.event.dao;

import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.event.model.EventTactic;
import us.deloitteinnovation.polaris.evaluator.event.model.EventType;

import java.util.List;

/**
 * Created by rbentaarit on 5/31/2017.
 */

public interface IEventDAO {
    EventTactic getEventTactics();
    EventType getEventTypes();
    Integer insertEvent(Event event);
    Event getEventById(Integer eventId);
    Boolean isEventUpdatable(Integer eventId);
    void updateEvent(Event event);
    void updateEvents(List<Event> events);
    Integer deleteEvent(Integer eventId);
    List<Event> allEvents(Integer offset, Integer size);
    Event getEventByNameAndAuthor(String eventName);
}
