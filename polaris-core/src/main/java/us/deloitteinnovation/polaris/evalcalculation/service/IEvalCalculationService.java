package us.deloitteinnovation.polaris.evalcalculation.service;

import us.deloitteinnovation.polaris.evalcalculation.model.CalculationResult;
import us.deloitteinnovation.polaris.evalcalculation.utils.EvalRequest;

/**
 * Created by rbentaarit on 8/29/2016.
 */
@FunctionalInterface
public interface IEvalCalculationService {

    CalculationResult calculate(EvalRequest request);
}
