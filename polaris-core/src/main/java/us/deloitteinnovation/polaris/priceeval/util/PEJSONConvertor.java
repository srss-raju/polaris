package us.deloitteinnovation.polaris.priceeval.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class PEJSONConvertor {

    private PEJSONConvertor(){
    }

    /**
     * @param jsonArray
     * @param groupByValue
     * @return JSONObject
     */
    @SuppressWarnings("unchecked")
    public static JSONObject convert(JSONArray jsonArray,String groupByValue){
        String previousAttribute = null;
        String attribute;
        JSONArray orderedJsonArray = null;
        boolean firstTimeFlag = true;
        JSONObject finalObject = new JSONObject();

        if(!jsonArray.isEmpty()){
            for(int i=0; i<jsonArray.size();i++){
                JSONObject object = (JSONObject)jsonArray.get(i);
                attribute = (String)object.get(groupByValue);
                if(attribute != null){
	                if(attribute.equals(previousAttribute)){
	                    orderedJsonArray.add(object);
	                }else if(!firstTimeFlag){
	                    orderedJsonArray = new JSONArray();
	                    orderedJsonArray.add(object);
	                }
                }
                if(firstTimeFlag){
                    orderedJsonArray = new JSONArray();
                    orderedJsonArray.add(object);
                    firstTimeFlag = false;
                }
                previousAttribute = attribute;
                finalObject.put(previousAttribute, orderedJsonArray);
            }
        }
        return finalObject;
    }

}
