package us.deloitteinnovation.polaris.filter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by rbentaarit on 2/13/2017.
 */
public class ChartFilter {
    private String header;
    @JsonProperty("items")
    private List<FilterItem> filterItems;

    public ChartFilter(String header, List<FilterItem> filterItems) {
        this.header = header;
        this.filterItems = filterItems;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<FilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<FilterItem> filterItems) {
        this.filterItems = filterItems;
    }
}
