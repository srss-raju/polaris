package us.deloitteinnovation.polaris.filter.model;

/**
 * Created by rbentaarit on 2/13/2017.
 */
public class FilterDetail {
    private static final String NULL_VALUE = "NULL";
    private String name;
    private boolean checked = true;

    public FilterDetail(String name, Boolean checked) {
        this.name = formatNullValue(name);
        this.checked = checked;
    }

    public FilterDetail() {
        // Default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = formatNullValue(name);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    // forced by UI requirements
    private String formatNullValue(String name){
        return name == null ? NULL_VALUE : name;
    }
}
