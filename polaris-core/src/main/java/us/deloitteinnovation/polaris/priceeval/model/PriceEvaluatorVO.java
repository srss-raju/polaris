package us.deloitteinnovation.polaris.priceeval.model;

import java.math.BigDecimal;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class PriceEvaluatorVO {

    private int customerId;

    private int productId;

    private String customerName;

    private String productName;

    private String productAttribute1;

    private String productAttribute2;

    private String productAttribute3;

    private String productAttribute4;

    private String productAttribute5;

    private float originalValue;

    private BigDecimal simulatedPrice;

    private double simulatedShelfPrice;

    private long simulatedUnits;

    private int externalSalesId;

    private int actualPromoValue;

    private int actualNonPromoValue;

    private int actualPromoUnits;

    private int actualNonPromoUnits;

    private int baselinePromoUnits;

    private BigDecimal coefficientValue;


    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
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
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }




    /**
     * @return the simulatedValue
     */
    public BigDecimal getSimulatedPrice() {
        return simulatedPrice;
    }

    /**
     * @param simulatedValue the simulatedValue to set
     */
    public void setSimulatedPrice(BigDecimal simulatedValue) {
        this.simulatedPrice = simulatedValue;
    }

    /**
     * @return the productAttribute1
     */
    public String getProductAttribute1() {
        return productAttribute1;
    }

    /**
     * @param productAttribute1 the productAttribute1 to set
     */
    public void setProductAttribute1(String productAttribute1) {
        this.productAttribute1 = productAttribute1;
    }

    /**
     * @return the simulatedShelfPrice
     */
    public double getSimulatedShelfPrice() {
        return simulatedShelfPrice;
    }

    /**
     * @param simulatedShelfPrice the simulatedShelfPrice to set
     */
    public void setSimulatedShelfPrice(double simulatedShelfPrice) {
        this.simulatedShelfPrice = simulatedShelfPrice;
    }

    /**
     * @return the simulatedUnits
     */
    public long getSimulatedUnits() {
        return simulatedUnits;
    }

    /**
     * @param simulatedUnits the simulatedUnits to set
     */
    public void setSimulatedUnits(long simulatedUnits) {
        this.simulatedUnits = simulatedUnits;
    }


    /**
     * @return the productAttribute2
     */
    public String getProductAttribute2() {
        return productAttribute2;
    }

    /**
     * @param productAttribute2 the productAttribute2 to set
     */
    public void setProductAttribute2(String productAttribute2) {
        this.productAttribute2 = productAttribute2;
    }

    /**
     * @return the productAttribute3
     */
    public String getProductAttribute3() {
        return productAttribute3;
    }

    /**
     * @param productAttribute3 the productAttribute3 to set
     */
    public void setProductAttribute3(String productAttribute3) {
        this.productAttribute3 = productAttribute3;
    }

    /**
     * @return the productAttribute4
     */
    public String getProductAttribute4() {
        return productAttribute4;
    }

    /**
     * @param productAttribute4 the productAttribute4 to set
     */
    public void setProductAttribute4(String productAttribute4) {
        this.productAttribute4 = productAttribute4;
    }

    /**
     * @return the productAttribute5
     */
    public String getProductAttribute5() {
        return productAttribute5;
    }

    /**
     * @param productAttribute5 the productAttribute5 to set
     */
    public void setProductAttribute5(String productAttribute5) {
        this.productAttribute5 = productAttribute5;
    }

    /**
     * @return the originalValue
     */
    public float getOriginalValue() {
        return originalValue;
    }

    /**
     * @param originalValue the originalValue to set
     */
    public void setOriginalValue(float originalValue) {
        this.originalValue = originalValue;
    }

    /**
     * @return the externalSalesId
     */
    public int getExternalSalesId() {
        return externalSalesId;
    }

    /**
     * @param externalSalesId the externalSalesId to set
     */
    public void setExternalSalesId(int externalSalesId) {
        this.externalSalesId = externalSalesId;
    }

    /**
     * @return the actualPromoValue
     */
    public int getActualPromoValue() {
        return actualPromoValue;
    }

    /**
     * @param actualPromoValue the actualPromoValue to set
     */
    public void setActualPromoValue(int actualPromoValue) {
        this.actualPromoValue = actualPromoValue;
    }

    /**
     * @return the actualNonPromoValue
     */
    public int getActualNonPromoValue() {
        return actualNonPromoValue;
    }

    /**
     * @param actualNonPromoValue the actualNonPromoValue to set
     */
    public void setActualNonPromoValue(int actualNonPromoValue) {
        this.actualNonPromoValue = actualNonPromoValue;
    }

    /**
     * @return the actualPromoUnits
     */
    public int getActualPromoUnits() {
        return actualPromoUnits;
    }

    /**
     * @param actualPromoUnits the actualPromoUnits to set
     */
    public void setActualPromoUnits(int actualPromoUnits) {
        this.actualPromoUnits = actualPromoUnits;
    }

    /**
     * @return the actualNonPromoUnits
     */
    public int getActualNonPromoUnits() {
        return actualNonPromoUnits;
    }

    /**
     * @param actualNonPromoUnits the actualNonPromoUnits to set
     */
    public void setActualNonPromoUnits(int actualNonPromoUnits) {
        this.actualNonPromoUnits = actualNonPromoUnits;
    }

    /**
     * @return the baselinePromoUnits
     */
    public int getBaselinePromoUnits() {
        return baselinePromoUnits;
    }

    /**
     * @param baselinePromoUnits the baselinePromoUnits to set
     */
    public void setBaselinePromoUnits(int baselinePromoUnits) {
        this.baselinePromoUnits = baselinePromoUnits;
    }

    public BigDecimal getCoefficientValue() {
        return coefficientValue;
    }

    public void setCoefficientValue(BigDecimal coefficientValue) {
        this.coefficientValue = coefficientValue;
    }



}
