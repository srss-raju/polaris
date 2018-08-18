package us.deloitteinnovation.polaris.kpi.service;

import us.deloitteinnovation.polaris.kpi.model.KpiCustomer;

import java.util.List;

/**
 * Created by rbentaarit on 12/1/2016.
 */
@FunctionalInterface
public interface ICustomerKPIService {
    List<KpiCustomer> getNames();
}
