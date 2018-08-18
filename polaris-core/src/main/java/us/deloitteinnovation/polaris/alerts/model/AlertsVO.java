package us.deloitteinnovation.polaris.alerts.model;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class AlertsVO {


    private String metric;
    private String timePeriod;
    private String customerChannel;
    private String customerName;
    private String answer;
    private String value;


    /**
     * @return the metric
     */
    public String getMetric() {
        return metric;
    }
    /**
     * @param metric the metric to set
     */
    public void setMetric(String metric) {
        this.metric = metric;
    }
    /**
     * @return the timePeriod
     */
    public String getTimePeriod() {
        return timePeriod;
    }
    /**
     * @param timePeriod the timePeriod to set
     */
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }
    /**
     * @return the customerChannel
     */
    public String getCustomerChannel() {
        return customerChannel;
    }
    /**
     * @param customerChannel the customerChannel to set
     */
    public void setCustomerChannel(String customerChannel) {
        this.customerChannel = customerChannel;
    }
    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }
    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
