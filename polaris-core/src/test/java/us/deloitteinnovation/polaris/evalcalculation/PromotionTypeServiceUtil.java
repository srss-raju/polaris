package us.deloitteinnovation.polaris.evalcalculation;

import us.deloitteinnovation.polaris.evalcalculation.model.PromotionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundlapally on 23-05-2017.
 */
public class PromotionTypeServiceUtil {
    public static PromotionType getPromotionType(String name) {
        PromotionType promotionType = new PromotionType();
        if (null != name) {
            promotionType.setName(name);
        }

        return  promotionType;
    }

    public static List<PromotionType> promotionTypeList(int promotionTypeCount) {
        List<PromotionType> promotionTypes = new ArrayList<>();
        for (int i = 0; i < promotionTypeCount; i++) {
            promotionTypes.add(PromotionTypeServiceUtil.getPromotionType("name"));
        }
        return promotionTypes;
    }
}
