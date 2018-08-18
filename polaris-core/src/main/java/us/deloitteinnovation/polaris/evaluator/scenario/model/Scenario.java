package us.deloitteinnovation.polaris.evaluator.scenario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import us.deloitteinnovation.polaris.evaluator.calculation.model.Metric;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rbentaarit on 5/25/2017.
 */
public class Scenario {

    private Integer id;

    @NotNull
    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endDate;


    private Boolean isPlan;

    private Float grossRevenue;

    private Float availableFunding;

    private Metric metric;

    private List<Event> events;

    private List<Integer> eventIds;

    private Integer planId;

    private String currencyCode;

    private Float totalPromoSpend;

    private Float previousYearGrossRevenue;

    private Float previousYearPromoSpend;

    private Float ytdSpend;

    private Float previousYearYtdSpend;

    private Float remainingSpend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("isPlan")
    public Boolean getPlan() {
        return isPlan;
    }

    @JsonProperty("isPlan")
    public void setPlan(Boolean provided) {
        isPlan = provided;
    }

    public Float getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(Float grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public Float getAvailableFunding() {
        return availableFunding;
    }

    public void setAvailableFunding(Float availableFunding) {
        this.availableFunding = availableFunding;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public List<Event> getEvents() {
        return events == null ? new ArrayList<>(): events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public List<Integer> getEventIds() {
        return eventIds == null ? new ArrayList<>(): eventIds;
    }

    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Float getTotalPromoSpend() {
        return totalPromoSpend;
    }

    public void setTotalPromoSpend(Float totalPromoSpend) {
        this.totalPromoSpend = totalPromoSpend;
    }

    public Float getPreviousYearGrossRevenue() {
        return previousYearGrossRevenue;
    }

    public void setPreviousYearGrossRevenue(Float previousYearGrossRevenue) {
        this.previousYearGrossRevenue = previousYearGrossRevenue;
    }

    public Float getPreviousYearPromoSpend() {
        return previousYearPromoSpend;
    }

    public void setPreviousYearPromoSpend(Float previousYearPromoSpend) {
        this.previousYearPromoSpend = previousYearPromoSpend;
    }

    public Float getYtdSpend() {
        return ytdSpend;
    }

    public void setYtdSpend(Float ytdSpend) {
        this.ytdSpend = ytdSpend;
    }

    public Float getPreviousYearYtdSpend() {
        return previousYearYtdSpend;
    }

    public void setPreviousYearYtdSpend(Float previousYearYtdSpend) {
        this.previousYearYtdSpend = previousYearYtdSpend;
    }

    public Float getRemainingSpend() {
        return remainingSpend;
    }

    public void setRemainingSpend(Float remainingSpend) {
        this.remainingSpend = remainingSpend;
    }
}

