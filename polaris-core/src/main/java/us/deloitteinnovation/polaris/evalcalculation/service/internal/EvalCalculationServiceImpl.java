package us.deloitteinnovation.polaris.evalcalculation.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.deloitteinnovation.polaris.evalcalculation.dao.IVolumeDecompCoefficientDAO;
import us.deloitteinnovation.polaris.evalcalculation.dao.IVolumeDecompDetailDAO;
import us.deloitteinnovation.polaris.evalcalculation.exception.EvalCalculationException;
import us.deloitteinnovation.polaris.evalcalculation.model.CalculationResult;
import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompCoefficient;
import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompDetail;
import us.deloitteinnovation.polaris.evalcalculation.service.IEvalCalculationService;
import us.deloitteinnovation.polaris.evalcalculation.utils.EvalRequest;

import java.text.MessageFormat;

import static java.lang.Boolean.FALSE;

/**
 * Created by rbentaarit on 8/30/2016.
 */

@Service
public class EvalCalculationServiceImpl implements IEvalCalculationService {

    private final IVolumeDecompDetailDAO volumeDecompDetailDAO;
    private final IVolumeDecompCoefficientDAO volumeDecompCoefficientDAO;

    @Autowired
    public EvalCalculationServiceImpl(IVolumeDecompDetailDAO volumeDecompDetailDAO, IVolumeDecompCoefficientDAO volumeDecompCoefficientDAO) {
        this.volumeDecompDetailDAO = volumeDecompDetailDAO;
        this.volumeDecompCoefficientDAO = volumeDecompCoefficientDAO;
    }

    @Override
    @Transactional
    public CalculationResult calculate(EvalRequest request){

        CalculationResult result = new CalculationResult();

        VolumeDecompDetail originalVolumeDecompDetail = volumeDecompDetailDAO.findByPromotion(request.getCustomerId(), request.getOriginalPromotion().getProductId(), request.getOriginalPromotion().getTactic(), request.getOriginalPromotion().getType(), request.getOriginalPromotion().getDate());
        VolumeDecompDetail targetVolumeDecompDetail = volumeDecompDetailDAO.findByPromotion(request.getCustomerId(), request.getTargetPromotion().getProductId(), request.getTargetPromotion().getTactic(), request.getTargetPromotion().getType(), request.getTargetPromotion().getDate());

        Double originalPredicate = calculatePredicate(originalVolumeDecompDetail, request.getOriginalPromotion().getPromotionPrice(), request.getOriginalPromotion().getNonPromotionPrice());
        Double targetPredicate = calculatePredicate(targetVolumeDecompDetail, request.getTargetPromotion().getPromotionPrice(), request.getTargetPromotion().getNonPromotionPrice());

        if (originalPredicate != null && originalPredicate != 0) {
            result.setOriginalVolume(originalPredicate);
            result.setPredictedVolume(targetPredicate);
            result.setLift(targetPredicate - originalPredicate);
            result.setLiftPercentage(MessageFormat.format("{0,number,#.##}", (targetPredicate - originalPredicate) / originalPredicate * 100));
            result.setDatePromotion(originalVolumeDecompDetail.getDate().toString());
            return result;
        }
        throw new EvalCalculationException("Calculation Error");
    }

    private Double calculatePredicate(VolumeDecompDetail volumeDecompDetail, Float promotionPrice, Float nonPromotionPrice){
        VolumeDecompCoefficient coefficient = volumeDecompCoefficientDAO.findFirst();
        Double promoPriceLog = Math.log(promotionPrice);
        Double nonPromoPriceLog = Math.log(nonPromotionPrice);
        return Math.exp(promoPriceLog *
                            (coefficient.getCpp() * volumeDecompDetail.isCp().compareTo(FALSE)
                            + coefficient.getCpv1() * volumeDecompDetail.isPv1().compareTo(FALSE)
                            + coefficient.getCpv2() * volumeDecompDetail.isPv2().compareTo(FALSE)
                            + coefficient.getCpv3() * volumeDecompDetail.isPv3().compareTo(FALSE)
                            + coefficient.getCpv4() * volumeDecompDetail.isPv4().compareTo(FALSE)
                            + coefficient.getCpv5() * volumeDecompDetail.isPv5().compareTo(FALSE))
                        + nonPromoPriceLog *
                            (coefficient.getCnpp() * volumeDecompDetail.isCnp().compareTo(FALSE)
                            + coefficient.getCnpv1() * volumeDecompDetail.isNpv1().compareTo(FALSE)
                            + coefficient.getCnpv2() * volumeDecompDetail.isNpv2().compareTo(FALSE)
                            + coefficient.getCnpv3() * volumeDecompDetail.isNpv3().compareTo(FALSE)
                            + coefficient.getCnpv4() * volumeDecompDetail.isNpv4().compareTo(FALSE)
                            + coefficient.getCnpv5() * volumeDecompDetail.isNpv5().compareTo(FALSE)))
                        * volumeDecompDetail.getV();
    }
    
}