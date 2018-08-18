package us.deloitteinnovation.polaris.evaluator.product.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evaluator.calculation.service.ICalculationService;
import us.deloitteinnovation.polaris.evaluator.event.dao.IEventDAO;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.product.dao.ISimProductDAO;
import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;
import us.deloitteinnovation.polaris.evaluator.product.service.ISimProductService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 6/11/2017.
 */

@Service
public class SimProductServiceImpl implements ISimProductService {

    private final ISimProductDAO simProductDAO;
    private final ICalculationService calculation;
    private final IEventDAO eventDAO;

    @Autowired
    public SimProductServiceImpl(ISimProductDAO simProductDAO, ICalculationService calculation, IEventDAO eventDAO) {
        this.simProductDAO = simProductDAO;
        this.calculation = calculation;
        this.eventDAO = eventDAO;
    }

    @Override
    public int[] insertSimProducts(List<SimProduct> simProducts) {
        return simProductDAO.insertProducts(simProducts);
    }

    @Transactional
    public Event insertSimProducts(Integer eventId, List<SimProduct> products) {
        if (eventDAO.isEventUpdatable(eventId)){
            simProductDAO.insertProducts(products);
            Event event = eventDAO.getEventById(eventId);
            event.setProducts(simProductDAO.getSimProductsByEventId(eventId));
            return event;
        }
        else {
            Event event = eventDAO.getEventById(eventId);
            event.setProducts(products);
            Integer copyId = eventDAO.insertEvent(event);
            event.getProducts().forEach(e -> e.setEventId(copyId));
            simProductDAO.insertProducts(event.getProducts());
            event.setProducts(getSimProductsByEventId(copyId));
            return event;
        }

    }

    @Override
    public List<SimProduct> getSimProductsByEventId(Integer eventId) {
        List<SimProduct> simProducts = simProductDAO.getSimProductsByEventId(eventId);
        simProducts.forEach(simProduct -> simProduct.setMetric(calculation.calculateProduct(simProduct.getId())));
        return simProducts;
    }

    @Override
    public SimProduct insertSimProduct(SimProduct product) {
        Integer simProductId = simProductDAO.insertSimProduct(product);
        return getSimProductById(simProductId);
    }

    @Override
    public SimProduct getSimProductById(Integer simProductId) {
        return simProductDAO.getSimProductById(simProductId);
    }

    @Override
    public Boolean deleteSimProduct(Integer simProductId) {
        return simProductDAO.deleteSimProduct(simProductId) == 1;
    }

    @Override
    public int[] updateSimProducts(List<SimProduct> simProducts) {
        return simProductDAO.updateSimProduct(simProducts);
    }


}
