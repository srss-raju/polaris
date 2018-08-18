package us.deloitteinnovation.polaris.evalcalculation.model;

/**
 * Created by rbentaarit on 9/20/2016.
 */
public class CalculationResult {

    private Double originalVolume;
    private Double predictedVolume;
    private Double lift;
    private String liftPercentage;
    private String datePromotion;


    public Double getOriginalVolume() {
        return originalVolume;
    }

    public void setOriginalVolume(Double originalVolume) {
        this.originalVolume = originalVolume;
    }

    public Double getPredictedVolume() {
        return predictedVolume;
    }

    public void setPredictedVolume(Double predictedVolume) {
        this.predictedVolume = predictedVolume;
    }

    public Double getLift() {
        return lift;
    }

    public void setLift(Double lift) {
        this.lift = lift;
    }

    public String getLiftPercentage() {
        return liftPercentage;
    }

    public void setLiftPercentage(String liftPercentage) {
        this.liftPercentage = liftPercentage;
    }

    public String getDatePromotion() {
        return datePromotion;
    }

    public void setDatePromotion(String datePromotion) {
        this.datePromotion = datePromotion;
    }
}
