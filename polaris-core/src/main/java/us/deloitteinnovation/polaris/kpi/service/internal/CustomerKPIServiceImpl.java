package us.deloitteinnovation.polaris.kpi.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.kpi.dao.ICustomerKPIDAO;
import us.deloitteinnovation.polaris.kpi.model.KpiCustomer;
import us.deloitteinnovation.polaris.kpi.service.ICustomerKPIService;

import java.util.List;

/**
 * Created by rbentaarit on 12/1/2016.
 */
@Service
public class CustomerKPIServiceImpl implements ICustomerKPIService {

    @Autowired
    ICustomerKPIDAO customerKPIDAO;

    @Override
    public List<KpiCustomer> getNames() {
        return customerKPIDAO.getNames();
    }
}
