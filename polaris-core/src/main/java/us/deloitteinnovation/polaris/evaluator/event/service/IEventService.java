package us.deloitteinnovation.polaris.evaluator.event.service;

import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.event.model.EventTactic;
import us.deloitteinnovation.polaris.evaluator.event.model.EventType;
import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;

import java.util.List;

/**
 * Created by rbentaarit on 5/31/2017.
 */
public interface IEventService {

    EventTactic getEventTactics();
    EventType getEventTypes();
    Event getEventById(Integer eventId);
    Event insertEvent(Event event);
    Event updateEvent(Integer eventId, Event event);
    Boolean deleteEvent(Integer eventId);
    Boolean isUpdatable(Integer eventId);
    Event copyEvent(Event event);
    Event updateSimProducts(Integer eventId, List<SimProduct> simProducts);
    Event insertSimProducts(Integer eventId, List<SimProduct> simProducts);
    Event insertSimProduct(Integer eventId, SimProduct simProduct);
    List<Event> allEvents(Integer offset, Integer size);
}
