package us.deloitteinnovation.polaris.eventeval.dao.internal;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import us.deloitteinnovation.polaris.eventeval.dao.IEventEvalDAO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalPromotionVO;
import us.deloitteinnovation.polaris.eventeval.model.EventEvalSimulatedPromotionVO;
import us.deloitteinnovation.polaris.eventeval.util.EventEvalCalcUtil;
import us.deloitteinnovation.polaris.eventeval.util.EventEvalPromotionMapper;
import us.deloitteinnovation.polaris.common.util.Constant;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Repository
public class EventEvalDAOImpl implements IEventEvalDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventEvalDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getEventOptimizerPromotionDetails(int productId, int customerId, int promotionId) {
        EventEvalPromotionVO eventEvalPromotionVO = jdbcTemplate.queryForObject(Constant.SQL_GET_COEFFICIENT_AND_PROMOTION, new Integer[]{productId, customerId, promotionId}, new EventEvalPromotionMapper());
        return EventEvalCalcUtil.getEventOptimizerPromoDetails(eventEvalPromotionVO);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public JSONObject saveEventOptimizerPromotionDetails(EventEvalPromotionVO eventEvalPromotionVO) {
        JSONObject saveResult = new JSONObject();
        int result = jdbcTemplate.update(Constant.SQL_SAVE_EVENT_OPTIM_PROMO_DETAILS, eventEvalPromotionVO.getSimulatedEndDate(), eventEvalPromotionVO.getSimulatedStartDate(), eventEvalPromotionVO.getSimulatedPromotionTactic(), eventEvalPromotionVO.getSimulatedPromotionUnits(), eventEvalPromotionVO.getSimulatedVolumeLiftCoefficient(), eventEvalPromotionVO.getSimulatedPromotionPrice(), 1, eventEvalPromotionVO.getProductId(), eventEvalPromotionVO.getPromotionId());
        saveResult.put("success", result+" Records Saved Successfully");
        return saveResult;
    }

    @Override
    public String getEventOptimizerSimulatedPromotionDetails(EventEvalSimulatedPromotionVO eventEvalSimulatedPromotionVO) {
        EventEvalPromotionVO eventEvalPromotionVO = jdbcTemplate.queryForObject(Constant.SQL_GET_COEFFICIENT_AND_PROMOTION, new Integer[]{eventEvalSimulatedPromotionVO.getProductId(), eventEvalSimulatedPromotionVO.getCustomerId(), eventEvalSimulatedPromotionVO.getPromotionId()}, new EventEvalPromotionMapper());
        return EventEvalCalcUtil.getEventOptimizerSimulatedPromoDetails(eventEvalPromotionVO, eventEvalSimulatedPromotionVO);
    }

}
