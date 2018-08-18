package us.deloitteinnovation.polaris.evaluator.calculation.model;

/**
 * Created by rbentaarit on 6/13/2017.
 */
public class ValueDetails {
    private String name;
    private Double fullValue;
    private Double value;
    private String type;
    private String currencyCode;
    private Character unit;

    public ValueDetails(String name, Double fullValue, Double value, String type, String currencyCode, Character unit) {
        this.name = name;
        this.fullValue = fullValue;
        this.value = value;
        this.type = type;
        this.currencyCode = currencyCode;
        this.unit = unit;
    }

    public ValueDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFullValue() {
        return fullValue;
    }

    public void setFullValue(Double fullValue) {
        this.fullValue = fullValue;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Character getUnit() {
        return unit;
    }

    public void setUnit(Character unit) {
        this.unit = unit;
    }
}
