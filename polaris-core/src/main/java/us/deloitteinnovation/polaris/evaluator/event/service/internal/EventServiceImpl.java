package us.deloitteinnovation.polaris.evaluator.event.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evaluator.calculation.service.ICalculationService;
import us.deloitteinnovation.polaris.evaluator.event.dao.IEventDAO;
import us.deloitteinnovation.polaris.evaluator.event.error.DuplicateProductsInEventException;
import us.deloitteinnovation.polaris.evaluator.event.error.EventNameCheckException;
import us.deloitteinnovation.polaris.evaluator.event.model.Event;
import us.deloitteinnovation.polaris.evaluator.event.model.EventTactic;
import us.deloitteinnovation.polaris.evaluator.event.model.EventType;
import us.deloitteinnovation.polaris.evaluator.event.service.IEventService;
import us.deloitteinnovation.polaris.evaluator.product.model.SimProduct;
import us.deloitteinnovation.polaris.evaluator.product.service.ISimProductService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rbentaarit on 5/31/2017.
 */
@Service
public class EventServiceImpl implements IEventService{

    public static final String
            PRODUCT_S_ADDED_IN_EVENT_SHOULD_BE_UNIQUE = "Product(s) added in Event should be unique.";
    public static final String EVENT_NAME_SHOULD_BE_UNIQUE = "Event Name should be unique.";
    private final IEventDAO eventDAO;

    private final ISimProductService simProductService;

    private final ICalculationService calculationService;

    @Autowired
    public EventServiceImpl(IEventDAO eventDAO, ISimProductService simProductService, ICalculationService calculationService) {
        this.eventDAO = eventDAO;
        this.simProductService = simProductService;
        this.calculationService = calculationService;
    }

    @Override
    public EventTactic getEventTactics() {
        return eventDAO.getEventTactics();
    }

    @Override
    public EventType getEventTypes() {
        return eventDAO.getEventTypes();
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventDAO.getEventById(eventId);
        event.setMetric(calculationService.calculateEvent(eventId));
        event.setProducts(simProductService.getSimProductsByEventId(eventId));
        return event;
    }

    @Transactional
    @Override
    public Event insertEvent(Event event) {
        checkUniqueProducts(event);
        if(eventDAO.getEventByNameAndAuthor(event.getName()) != null) {
            throw new EventNameCheckException(EVENT_NAME_SHOULD_BE_UNIQUE);
        }

        Integer eventId =  eventDAO.insertEvent(event);
        if (event.getProducts() != null && !event.getProducts().isEmpty()) {
            event.getProducts().forEach(simProduct -> simProduct.setEventId(eventId));
            simProductService.insertSimProducts(event.getProducts());
        }
        return getEventById(eventId);
    }

    private void checkUniqueProducts(Event event) {
        List<SimProduct> products = event.getProducts();
        Set<Integer> productFinal = new HashSet<>() ;
        products.stream().forEach(product -> productFinal.add(product.getProductId()));

        if(productFinal.size() != products.size()) {
            throw new DuplicateProductsInEventException(PRODUCT_S_ADDED_IN_EVENT_SHOULD_BE_UNIQUE);
        }
    }

    @Override
    public Event updateEvent(Integer eventId, Event event) {
        checkUniqueProducts(event);
        event.setId(eventId);
        eventDAO.updateEvent(event);
        return getEventById(eventId);
    }

    @Override
    public Event copyEvent(Event event){
        event.setProducts(simProductService.getSimProductsByEventId(event.getPromoId()));
        return insertEvent(event);
    }

    @Override
    public Event updateSimProducts(Integer eventId, List<SimProduct> simProducts) {
        simProducts.forEach(p -> p.setEventId(eventId));
        simProductService.updateSimProducts(simProducts);
        return getEventById(eventId);
    }

    @Override
    public Boolean deleteEvent(Integer eventId) {
        return eventDAO.deleteEvent(eventId) == 1;
    }

    @Override
    public Boolean isUpdatable(Integer eventId) {
        return eventDAO.isEventUpdatable(eventId);
    }

    @Override
    public Event insertSimProducts(Integer eventId, List<SimProduct> products) {
        if (isUpdatable(eventId)){
           simProductService.insertSimProducts(products);
            return getEventById(eventId);
        }
        else {
            Event event = eventDAO.getEventById(eventId);
            event.setProducts(products);
            Integer copyId = eventDAO.insertEvent(event);
            event.getProducts().forEach(e -> e.setEventId(copyId));
            return insertEvent(event);
        }
    }

    @Override
    public Event insertSimProduct(Integer eventId, SimProduct simProduct) {
        simProduct.setEventId(eventId);
        simProductService.insertSimProduct(simProduct);
        return getEventById(eventId);
    }

    @Override
    public List<Event> allEvents(Integer offset, Integer size) {
        return eventDAO.allEvents(offset, size);
    }

}
