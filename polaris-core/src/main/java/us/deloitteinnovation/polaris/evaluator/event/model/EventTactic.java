package us.deloitteinnovation.polaris.evaluator.event.model;

import java.util.List;

/**
 * Created by rbentaarit on 5/31/2017.
 */
public class EventTactic {

    public EventTactic(List<String> tactics) {
        this.tactics = tactics;
    }

    private List<String> tactics;

    public List<String> getTactics() {
        return tactics;
    }

    public void setTactics(List<String> tactics) {
        this.tactics = tactics;
    }
}
