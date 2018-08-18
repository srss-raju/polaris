package us.deloitteinnovation.polaris.evaluator.calculation.service.internal;

import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evaluator.calculation.model.Metric;
import us.deloitteinnovation.polaris.evaluator.calculation.model.MetricBuilder;
import us.deloitteinnovation.polaris.evaluator.calculation.model.ValueDetails;
import us.deloitteinnovation.polaris.evaluator.calculation.service.ICalculationService;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;

/**
 * Created by rbentaarit on 6/13/2017.
 */

@Service
public class CalculationServiceImpl implements ICalculationService {
    private static final String PERCENTAGE = "percentage";
    private static final String VOLUME_LIFT = "VolumeLift";
    private static final String FINANCIAL = "financial";
    private static final String QUANTITY = "quantity";


    @Override
    public Metric calculateEvent(Integer eventId) {
        return mockedMetric();
    }

    @Override
    public Metric calculateProduct(Integer simProductId) {
        return new MetricBuilder().setRoi(new ValueDetails("Spend",20.4,20.4,PERCENTAGE,null,null))
                .setSpend(new ValueDetails("Spend",5000d,5.0, FINANCIAL,"USD",'K'))
                .setCurrentVSOD(new ValueDetails("VSOD",5300d,5.3, QUANTITY,null,'K'))
                .setBaselineVSOD(new ValueDetails("Baseline VSOD",5500D,55.2, QUANTITY,null,'K'))
                .setIncrementalVSOD(new ValueDetails("Incremental VSOD",1000d,10.0, QUANTITY,null,'K'))
                .setCurrentSales(new ValueDetails("Sales",37000d,3.7, FINANCIAL,"USD",'K'))
                .setVolumeLift(new ValueDetails(VOLUME_LIFT,1.3d,1.3,PERCENTAGE,null,null))
                .createMetric();
    }

    @Override
    public Metric calculateScenario(Integer scenarioId) {
        return mockedMetric();
    }

    @Override
    public Event simulateEvent(Event event) {
       event.setMetric(calculateEvent(event.getId()));
       return event;
    }

    @Override
    public Scenario simulateScenario(Scenario scenario){
        scenario.setMetric(mockedMetric());
        return scenario;
    }

    private Metric mockedMetric(){
        return new MetricBuilder()
                //Roi
                .setRoi(new ValueDetails("ROI",55.2,55.2,PERCENTAGE,null,null))
                .setDiscountDepth(new ValueDetails("Discount Depth",55.2,55.2,PERCENTAGE,null,null))
                .setTotalDiscount(new ValueDetails("Total Discount",43200D,43.2, FINANCIAL,"USD",'K'))
                .setSpend(new ValueDetails("Promo Spend",102000034D,10.2, FINANCIAL,"USD",'M'))
                //Own
                .setMargin(new ValueDetails("Own Margin",100334d,100.3, FINANCIAL,"USD",'K'))
                .setMarginPercentage(new ValueDetails("Own Margin(%)",12.2,12.2,PERCENTAGE,null,null))
                .setNetRevenue(new ValueDetails("Net Revenue",100334d,100.3, FINANCIAL,"USD",'K'))
                .setGrossRevenue(new ValueDetails("Gross Revenue",100334d,100.3, FINANCIAL,"USD",'K'))
                //VSOD
                .setCurrentVSOD(new ValueDetails("Current VSOD",40200D,40.2, QUANTITY,null,'K'))
                .setBaselineVSOD(new ValueDetails("Baseline VSOD",55200D,55.2, QUANTITY,null,'K'))
                .setIncrementalVSOD(new ValueDetails("Incremental VSOD",10000d,10.0, QUANTITY,null,'K'))
                //RetailerSales
                .setCurrentSales(new ValueDetails("Current Sales",100334d,100.3, FINANCIAL,"USD",'K'))
                .setRetailerMargin(new ValueDetails("Retailer Margin",100334d,100.3, FINANCIAL,"USD",'K'))
                .setRetailerMarginPercentage(new ValueDetails("Retailer Margin(%)",12.2,12.2,PERCENTAGE,null,null))
                .setDistributorMargin(new ValueDetails("Distributor Margin",100334d,100.3, FINANCIAL,"USD",'K'))
                .setDistributorMarginPercentage(new ValueDetails("Distributor Margin(%)",12.2,12.2,PERCENTAGE,null,null))
                //Volume lift
                .setVolumeLift(new ValueDetails(VOLUME_LIFT,20.2,20.2, PERCENTAGE,null,null))
                .createMetric();
    }

}
