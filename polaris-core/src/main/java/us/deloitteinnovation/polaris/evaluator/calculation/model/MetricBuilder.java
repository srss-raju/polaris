package us.deloitteinnovation.polaris.evaluator.calculation.model;

public class MetricBuilder {
    private ValueDetails roi;
    private ValueDetails discountDepth;
    private ValueDetails totalDiscount;
    private ValueDetails spend;
    private ValueDetails margin;
    private ValueDetails marginPercentage;
    private ValueDetails netRevenue;
    private ValueDetails grossRevenue;
    private ValueDetails currentVSOD;
    private ValueDetails baselineVSOD;
    private ValueDetails incrementalVSOD;
    private ValueDetails currentSales;
    private ValueDetails retailerMargin;
    private ValueDetails retailerMarginPercentage;
    private ValueDetails distributorMargin;
    private ValueDetails distributorMarginPercentage;
    private ValueDetails volumeLift;

    public MetricBuilder setRoi(ValueDetails roi) {
        this.roi = roi;
        return this;
    }

    public MetricBuilder setDiscountDepth(ValueDetails discountDepth) {
        this.discountDepth = discountDepth;
        return this;
    }

    public MetricBuilder setTotalDiscount(ValueDetails totalDiscount) {
        this.totalDiscount = totalDiscount;
        return this;
    }

    public MetricBuilder setSpend(ValueDetails spend) {
        this.spend = spend;
        return this;
    }

    public MetricBuilder setMargin(ValueDetails margin) {
        this.margin = margin;
        return this;
    }

    public MetricBuilder setMarginPercentage(ValueDetails marginPercentage) {
        this.marginPercentage = marginPercentage;
        return this;
    }

    public MetricBuilder setNetRevenue(ValueDetails netRevenue) {
        this.netRevenue = netRevenue;
        return this;
    }

    public MetricBuilder setGrossRevenue(ValueDetails grossRevenue) {
        this.grossRevenue = grossRevenue;
        return this;
    }

    public MetricBuilder setCurrentVSOD(ValueDetails currentVSOD) {
        this.currentVSOD = currentVSOD;
        return this;
    }

    public MetricBuilder setBaselineVSOD(ValueDetails baselineVSOD) {
        this.baselineVSOD = baselineVSOD;
        return this;
    }

    public MetricBuilder setIncrementalVSOD(ValueDetails incrementalVSOD) {
        this.incrementalVSOD = incrementalVSOD;
        return this;
    }

    public MetricBuilder setCurrentSales(ValueDetails currentSales) {
        this.currentSales = currentSales;
        return this;
    }

    public MetricBuilder setRetailerMargin(ValueDetails retailerMargin) {
        this.retailerMargin = retailerMargin;
        return this;
    }

    public MetricBuilder setRetailerMarginPercentage(ValueDetails retailerMarginPercentage) {
        this.retailerMarginPercentage = retailerMarginPercentage;
        return this;
    }

    public MetricBuilder setDistributorMargin(ValueDetails distributorMargin) {
        this.distributorMargin = distributorMargin;
        return this;
    }

    public MetricBuilder setDistributorMarginPercentage(ValueDetails distributorMarginPercentage) {
        this.distributorMarginPercentage = distributorMarginPercentage;
        return this;
    }

    public MetricBuilder setVolumeLift(ValueDetails volumeLift) {
        this.volumeLift = volumeLift;
        return this;
    }

    public Metric createMetric() {
        return new Metric(roi, discountDepth, totalDiscount, spend, margin, marginPercentage, netRevenue, grossRevenue, currentVSOD, baselineVSOD, incrementalVSOD, currentSales, retailerMargin, retailerMarginPercentage, distributorMargin, distributorMarginPercentage, volumeLift);
    }
}