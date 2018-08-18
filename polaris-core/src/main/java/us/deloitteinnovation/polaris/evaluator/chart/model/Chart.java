package us.deloitteinnovation.polaris.evaluator.chart.model;

import us.deloitteinnovation.polaris.evaluator.calculation.model.ValueDetails;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by rbentaarit on 7/6/2017.
 */
public class Chart {

    private Integer id;
    private Map<LocalDate,ValueDetails> weeklyValues;
    private Map<LocalDate, ValueDetails> monthlyValues;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<LocalDate, ValueDetails> getWeeklyValues() {
        return weeklyValues;
    }

    public void setWeeklyValues(Map<LocalDate, ValueDetails> weeklyValues) {
        this.weeklyValues = weeklyValues;
    }

    public Map<LocalDate, ValueDetails> getMonthlyValues() {
        return monthlyValues;
    }

    public void setMonthlyValues(Map<LocalDate, ValueDetails> monthlyValues) {
        this.monthlyValues = monthlyValues;
    }
}
