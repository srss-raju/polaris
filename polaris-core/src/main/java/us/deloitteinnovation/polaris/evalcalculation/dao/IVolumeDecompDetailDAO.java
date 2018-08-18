package us.deloitteinnovation.polaris.evalcalculation.dao;

import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompDetail;

/**
 * Created by rbentaarit on 8/31/2016.
 */

@FunctionalInterface
public interface IVolumeDecompDetailDAO {
    VolumeDecompDetail findByPromotion(int customerId, int productId, String tactic, String type, String date);

}
