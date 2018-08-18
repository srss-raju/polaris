package us.deloitteinnovation.polaris.evalcalculation;

import us.deloitteinnovation.polaris.evalcalculation.model.PromotionTactic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundlapally on 23-05-2017.
 */
public class PromotionTacticServiceUtil {

    public static PromotionTactic getPromotion( String name) {
        PromotionTactic promotionTactic = new PromotionTactic();
        if (null != name) {
            promotionTactic.setName(name);
        }

        return  promotionTactic;
    }

    public static List<PromotionTactic> getPromotionTacticsList(int promotionTacticsCount) {
        List<PromotionTactic> promotionTactics = new ArrayList<>();
        for (int i = 0; i < promotionTacticsCount; i++) {
            promotionTactics.add(PromotionTacticServiceUtil.getPromotion("name"));
        }
        return promotionTactics;
    }
}
