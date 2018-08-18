package us.deloitteinnovation.polaris.evaluator.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import us.deloitteinnovation.polaris.evaluator.calculation.model.Metric;
import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by rbentaarit on 5/25/2017.
 */
public class Event {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String tactic;
    @NotNull
    private String type;
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startDate;
    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endDate;
    @NotNull
    private String campaignName;
    @NotNull
    private String campaignHoliday;
    private Integer promoId;
    private List<SimProduct> products;
    private Metric metric;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTactic() {
        return tactic;
    }

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignHoliday() {
        return campaignHoliday;
    }

    public void setCampaignHoliday(String campaignHoliday) {
        this.campaignHoliday = campaignHoliday;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public List<SimProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SimProduct> products) {
        this.products = products;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
