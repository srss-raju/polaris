package us.deloitteinnovation.polaris.filter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rbentaarit on 2/13/2017.
 */
public class FilterItem {
    private static final String FLAG_ALL = "ALL";
    private String name;
    @JsonProperty("data")
    private List<FilterDetail> filterDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilterDetail> getFilterDetails() {
        return filterDetails;
    }

    public void setFilterDetails(List<FilterDetail> filterDetails) {
        this.filterDetails = filterDetails;
        setGlobalFlag();
    }

    private void setGlobalFlag() {
        if (filterDetails != null) {
            filterDetails.removeAll(filterDetails.stream().filter(filterDetail -> FLAG_ALL.equals(filterDetail.getName())).collect(Collectors.toList()));
            filterDetails.add(new FilterDetail(FLAG_ALL, filterDetails.stream().filter(filterDetail -> filterDetail.isChecked()).count() == filterDetails.size()));
        }
    }
}
