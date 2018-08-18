package us.deloitteinnovation.polaris.evalcalculation.dao;

import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompCoefficient;

/**
 * Created by rbentaarit on 8/31/2016.
 */
@FunctionalInterface
public interface IVolumeDecompCoefficientDAO {
    VolumeDecompCoefficient findFirst();
}
