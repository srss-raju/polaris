package us.deloitteinnovation.polaris.evaluator.event.model;

import java.util.List;

/**
 * Created by rbentaarit on 5/31/2017.
 */
public class EventType {


    public EventType(List<String> types) {
        this.types = types;
    }

    private List<String> types;

    public List<String> getTypes() {
        return types;
    }

    public void setEvents(List<String> types) {
        this.types = types;
    }
}
