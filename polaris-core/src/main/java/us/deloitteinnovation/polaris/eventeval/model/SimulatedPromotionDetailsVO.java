package us.deloitteinnovation.polaris.eventeval.model;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class SimulatedPromotionDetailsVO {
    private final int simulatedPromotionUnits;
    private final float simulatedPrmotionDiscountDepth;
    private final float simulatedPromotionPrice;
    private final float simulatedPromotionValue;
    private final float simulatedPromotionIncrementalValue;
    private final int simulatedPromotionIncrementalUnits;
    private final float simulatedROI;
    private final  float simulatedMargin;
    private final float simulatedVolumeLiftCoefficient;
    private final float simulatedDepth; 
    private final String tactic;
    
    private SimulatedPromotionDetailsVO(SimulatedPromotionDetailsVOBuilder builder){
        this.simulatedPromotionUnits = builder.simulatedPromotionUnits;
        this.simulatedPrmotionDiscountDepth = builder.simulatedPrmotionDiscountDepth;
        this.simulatedPromotionPrice = builder.simulatedPromotionPrice;
        this.simulatedPromotionValue = builder.simulatedPromotionValue;
        this.simulatedPromotionIncrementalValue = builder.simulatedPromotionIncrementalValue;
        this.simulatedPromotionIncrementalUnits = builder.simulatedPromotionIncrementalUnits;
        this.simulatedROI = builder.simulatedROI;
        this.simulatedMargin = builder.simulatedMargin;
        this.simulatedVolumeLiftCoefficient = builder.simulatedVolumeLiftCoefficient;
        this.simulatedDepth = builder.simulatedDepth;
        this.tactic = builder.tactic;
    }
    
    /**
     * @return the simulatedPromotionUnits
     */
    public int getSimulatedPromotionUnits() {
        return simulatedPromotionUnits;
    }
    /**
     * @return the simulatedPrmotionDiscountDepth
     */
    public float getSimulatedPrmotionDiscountDepth() {
        return simulatedPrmotionDiscountDepth;
    }
    /**
     * @return the simulatedPromotionPrice
     */
    public float getSimulatedPromotionPrice() {
        return simulatedPromotionPrice;
    }
    /**
     * @return the simulatedPromotionValue
     */
    public float getSimulatedPromotionValue() {
        return simulatedPromotionValue;
    }
    /**
     * @return the simulatedPromotionIncrementalValue
     */
    public float getSimulatedPromotionIncrementalValue() {
        return simulatedPromotionIncrementalValue;
    }
    /**
     * @return the simulatedPromotionIncrementalUnits
     */
    public int getSimulatedPromotionIncrementalUnits() {
        return simulatedPromotionIncrementalUnits;
    }
    /**
     * @return the simulatedROI
     */
    public float getSimulatedROI() {
        return simulatedROI;
    }
    /**
     * @return the simulatedMargin
     */
    public float getSimulatedMargin() {
        return simulatedMargin;
    }
    /**
     * @return the simulatedVolumeLiftCoefficient
     */
    public float getSimulatedVolumeLiftCoefficient() {
        return simulatedVolumeLiftCoefficient;
    }
    /**
     * @return the simulatedDepth
     */
    public float getSimulatedDepth() {
        return simulatedDepth;
    }
    /**
     * @return the tactic
     */
    public String getTactic() {
        return tactic;
    }
    
    @Override
    public String toString() {
        return "SimulatedPromotionDetailsVO: " + this.simulatedPromotionUnits
                + ", " + this.simulatedPrmotionDiscountDepth + ", "
                + this.simulatedPromotionPrice + ", "
                + this.simulatedPromotionValue + ", "
                + this.simulatedPromotionIncrementalValue + ", "
                + this.simulatedPromotionIncrementalUnits + ", "
                + this.simulatedROI + ", " + this.simulatedMargin + ", "
                + this.simulatedVolumeLiftCoefficient + ", "
                + this.simulatedDepth + ", " + this.tactic;
    }
    
    /**
     * 
     * @author RajeshKumar B
     *
     */
    public static class SimulatedPromotionDetailsVOBuilder {
        private int simulatedPromotionUnits;
        private float simulatedPrmotionDiscountDepth;
        private float simulatedPromotionPrice;
        private float simulatedPromotionValue;
        private float simulatedPromotionIncrementalValue;
        private int simulatedPromotionIncrementalUnits;
        private float simulatedROI;
        private float simulatedMargin;
        private float simulatedVolumeLiftCoefficient;
        private float simulatedDepth; 
        private String tactic;
        
        public SimulatedPromotionDetailsVOBuilder() {
            //Do Nothing
        }
        
        /**
         * @param simulatedPromotionUnits
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedPromotionUnits(int simulatedPromotionUnits){
            this.simulatedPromotionUnits = simulatedPromotionUnits;
            return this;
        }
        
        /**
         * 
         * @param simulatedPrmotionDiscountDepth
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedPrmotionDiscountDepth(float simulatedPrmotionDiscountDepth){
            this.simulatedPrmotionDiscountDepth = simulatedPrmotionDiscountDepth;
            return this;
        }
        
        /**
         * 
         * @param simulatedPromotionPrice
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedPromotionPrice(float simulatedPromotionPrice){
            this.simulatedPromotionPrice = simulatedPromotionPrice;
            return this;
        }
        
        /**
         * 
         * @param simulatedPromotionValue
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedPromotionValue(float simulatedPromotionValue){
            this.simulatedPromotionValue = simulatedPromotionValue;
            return this;
        }
        
        /**
         * 
         * @param simulatedPromotionIncrementalValue
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedPromotionIncrementalValue(float simulatedPromotionIncrementalValue){
            this.simulatedPromotionIncrementalValue = simulatedPromotionIncrementalValue;
            return this;
        }
        
        /**
         * 
         * @param simulatedPromotionIncrementalUnits
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedPromotionIncrementalUnits(int simulatedPromotionIncrementalUnits){
            this.simulatedPromotionIncrementalUnits = simulatedPromotionIncrementalUnits;
            return this;
        }
        
        /**
         * 
         * @param simulatedROI
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedROI(float simulatedROI){
            this.simulatedROI = simulatedROI;
            return this;
        }
        
        /**
         * 
         * @param simulatedMargin
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedMargin(float simulatedMargin){
            this.simulatedMargin = simulatedMargin;
            return this;
        }
        
        /**
         * 
         * @param simulatedVolumeLiftCoefficient
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedVolumeLiftCoefficient(float simulatedVolumeLiftCoefficient){
            this.simulatedVolumeLiftCoefficient = simulatedVolumeLiftCoefficient;
            return this;
        }
        
        /**
         * 
         * @param simulatedDepth
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder simulatedDepth(float simulatedDepth){
            this.simulatedDepth = simulatedDepth;
            return this;
        }
        
        /**
         * 
         * @param tactic
         * @return SimulatedPromotionDetailsVOBuilder
         */
        public SimulatedPromotionDetailsVOBuilder tactic(String tactic){
            this.tactic = tactic;
            return this;
        }
        
        /**
         * 
         * @return SimulatedPromotionDetailsVO
         */
        public SimulatedPromotionDetailsVO build(){
            return new SimulatedPromotionDetailsVO(this);
        }
    }
}
