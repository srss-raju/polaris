package us.deloitteinnovation.polaris.evaluator.calculation.model;

/**
 * Created by rbentaarit on 6/13/2017.
 */
public class Metric {

    //ROI
    private ValueDetails roi; //%
    private ValueDetails discountDepth; //%
    private ValueDetails totalDiscount; //$
    private ValueDetails spend; //n
    //Own Margin
    private ValueDetails margin;
    private ValueDetails marginPercentage;
    private ValueDetails netRevenue;
    private ValueDetails grossRevenue;
    //VSOD
    private ValueDetails currentVSOD;
    private ValueDetails baselineVSOD;
    private ValueDetails incrementalVSOD;
    //RetailerSales
    private ValueDetails currentSales;
    private ValueDetails retailerMargin;
    private ValueDetails retailerMarginPercentage;
    private ValueDetails distributorMargin;
    private ValueDetails distributorMarginPercentage;
    //Volume lift
    private ValueDetails volumeLift;

    public Metric(ValueDetails roi, ValueDetails discountDepth, ValueDetails totalDiscount, ValueDetails spend, ValueDetails margin,
                  ValueDetails marginPercentage, ValueDetails netRevenue, ValueDetails grossRevenue, ValueDetails currentVSOD,
                  ValueDetails baselineVSOD, ValueDetails incrementalVSOD, ValueDetails currentSales, ValueDetails retailerMargin,
                  ValueDetails retailerMarginPercentage, ValueDetails distributorMargin, ValueDetails distributorMarginPercentage,
                  ValueDetails volumeLift) {
        this.roi = roi;
        this.discountDepth = discountDepth;
        this.totalDiscount = totalDiscount;
        this.spend = spend;
        this.margin = margin;
        this.marginPercentage = marginPercentage;
        this.netRevenue = netRevenue;
        this.grossRevenue = grossRevenue;
        this.currentVSOD = currentVSOD;
        this.baselineVSOD = baselineVSOD;
        this.incrementalVSOD = incrementalVSOD;
        this.currentSales = currentSales;
        this.retailerMargin = retailerMargin;
        this.retailerMarginPercentage = retailerMarginPercentage;
        this.distributorMargin = distributorMargin;
        this.distributorMarginPercentage = distributorMarginPercentage;
        this.volumeLift = volumeLift;
    }

    public Metric() {
    }

    public ValueDetails getRoi() {
        return roi;
    }

    public void setRoi(ValueDetails roi) {
        this.roi = roi;
    }

    public ValueDetails getDiscountDepth() {
        return discountDepth;
    }

    public void setDiscountDepth(ValueDetails discountDepth) {
        this.discountDepth = discountDepth;
    }

    public ValueDetails getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(ValueDetails totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public ValueDetails getSpend() {
        return spend;
    }

    public void setSpend(ValueDetails spend) {
        this.spend = spend;
    }

    public ValueDetails getMargin() {
        return margin;
    }

    public void setMargin(ValueDetails margin) {
        this.margin = margin;
    }

    public ValueDetails getMarginPercentage() {
        return marginPercentage;
    }

    public void setMarginPercentage(ValueDetails marginPercentage) {
        this.marginPercentage = marginPercentage;
    }

    public ValueDetails getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(ValueDetails netRevenue) {
        this.netRevenue = netRevenue;
    }

    public ValueDetails getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(ValueDetails grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public ValueDetails getCurrentVSOD() {
        return currentVSOD;
    }

    public void setCurrentVSOD(ValueDetails currentVSOD) {
        this.currentVSOD = currentVSOD;
    }

    public ValueDetails getBaselineVSOD() {
        return baselineVSOD;
    }

    public void setBaselineVSOD(ValueDetails baselineVSOD) {
        this.baselineVSOD = baselineVSOD;
    }

    public ValueDetails getIncrementalVSOD() {
        return incrementalVSOD;
    }

    public void setIncrementalVSOD(ValueDetails incrementalVSOD) {
        this.incrementalVSOD = incrementalVSOD;
    }

    public ValueDetails getCurrentSales() {
        return currentSales;
    }

    public void setCurrentSales(ValueDetails currentSales) {
        this.currentSales = currentSales;
    }

    public ValueDetails getRetailerMargin() {
        return retailerMargin;
    }

    public void setRetailerMargin(ValueDetails retailerMargin) {
        this.retailerMargin = retailerMargin;
    }

    public ValueDetails getRetailerMarginPercentage() {
        return retailerMarginPercentage;
    }

    public void setRetailerMarginPercentage(ValueDetails retailerMarginPercentage) {
        this.retailerMarginPercentage = retailerMarginPercentage;
    }

    public ValueDetails getDistributorMargin() {
        return distributorMargin;
    }

    public void setDistributorMargin(ValueDetails distributorMargin) {
        this.distributorMargin = distributorMargin;
    }

    public ValueDetails getDistributorMarginPercentage() {
        return distributorMarginPercentage;
    }

    public void setDistributorMarginPercentage(ValueDetails distributorMarginPercentage) {
        this.distributorMarginPercentage = distributorMarginPercentage;
    }

    public ValueDetails getVolumeLift() {
        return volumeLift;
    }

    public void setVolumeLift(ValueDetails volumeLift) {
        this.volumeLift = volumeLift;
    }
}
