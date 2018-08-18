package us.deloitteinnovation.polaris.priceeval.service.internal;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.priceeval.dao.IPriceEvalDAO;
import us.deloitteinnovation.polaris.priceeval.model.PriceEvaluatorVO;
import us.deloitteinnovation.polaris.priceeval.service.IPriceEvalService;

import java.util.List;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Service("priceEvalService")
public class PriceEvalServiceImpl implements IPriceEvalService {

    private static final Logger LOG = LoggerFactory.getLogger(PriceEvalServiceImpl.class);

    private IPriceEvalDAO priceEvalDAO;

    /**
     * 
     * @param priceEvalDAO
     */
    @Autowired
    public PriceEvalServiceImpl(IPriceEvalDAO priceEvalDAO) {
        this.priceEvalDAO = priceEvalDAO;
    }

    @Override
    public JSONObject getPriceEvaluatorDetails(int customerId, String productLevel, String productLevelValue, String groupByValue) {
        LOG.info("Entered:getPriceEvaluatorDetails --->>> ");
        return priceEvalDAO.getPriceEvaluatorDetails(customerId, productLevel, productLevelValue, groupByValue);
    }

    @Override
    public JSONObject savePriceEvaluatorDetails(List<PriceEvaluatorVO> priceEvaluatorVOList) {
        LOG.info("Entered: savePriceEvaluatorDetails --->>> ");
        return priceEvalDAO.savePriceEvaluatorDetails(priceEvaluatorVOList);
    }

    @Override
    public JSONObject getCustomers() {
        LOG.info("Entered: getCustomers --->>> ");
        return priceEvalDAO.getCustomers();
    }
}
