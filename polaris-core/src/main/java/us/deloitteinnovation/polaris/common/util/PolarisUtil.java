package us.deloitteinnovation.polaris.common.util;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.BasicTextEncryptor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.deloitteinnovation.polaris.alerts.model.AlertsVO;
import us.deloitteinnovation.polaris.bookmarks.model.Bookmark;


import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author RajeshKumar B
 *
 */
public class PolarisUtil {

    private static final Logger LOG = LoggerFactory
            .getLogger(PolarisUtil.class);
    Properties prop = new Properties();
    InputStream input = null;

    private PolarisUtil() {
    }

    /**
     * @param listvalue
     * @return JSONObject
     */
    @SuppressWarnings(Constant.UNCHECKED)
    public static JSONObject getAlertJsonObject(List<AlertsVO> listvalue) {
        JSONArray jsonArray = new JSONArray();
        JSONObject alertsJson = new JSONObject();
            for (int i = 0; i < listvalue.size(); i++) {
                JSONObject alertDetailsJson = new JSONObject();
                alertDetailsJson.put(Constant.METRIC, listvalue.get(i).getMetric());
                alertDetailsJson.put(Constant.TIMEPERIOD, listvalue.get(i).getTimePeriod());
                alertDetailsJson.put(Constant.CUSTOMERCHANNEL, listvalue.get(i).getCustomerChannel());
                alertDetailsJson.put(Constant.CUSTOMERNAME, listvalue.get(i).getCustomerName());
                alertDetailsJson.put(Constant.ANSWER, listvalue.get(i).getAnswer());
                alertDetailsJson.put(Constant.VALUE, listvalue.get(i).getValue());
                jsonArray.add(alertDetailsJson);
            }
            alertsJson.put("alerts", jsonArray);
        return alertsJson;
    }

    /**
     * @param bookmarkList
     * This method will convert list of Bookmarks to Json array
     * @return JSONArray
     */
    @SuppressWarnings(Constant.UNCHECKED)
    public static JSONArray getBookmarksJSON(List<Bookmark> bookmarkList) {
        JSONArray bookmarksJsonArray = new JSONArray();
        for(Bookmark bookmark : bookmarkList){
            JSONObject bookmarksJsonObject = new JSONObject();
            bookmarksJsonObject.put("bookmarksId", bookmark.getBookmarksID());
            bookmarksJsonObject.put("linkName", bookmark.getLinkName());
            bookmarksJsonObject.put("state", bookmark.getState());
            bookmarksJsonObject.put("chartName", bookmark.getChartName());
            bookmarksJsonObject.put("userId", bookmark.getUserID());
            bookmarksJsonObject.put("timeStamp", bookmark.getTimeStamp());
            bookmarksJsonObject.put("privateFlag", bookmark.isPrivateFlag());
            bookmarksJsonArray.add(bookmarksJsonObject);
        }
        return bookmarksJsonArray;
    }

    /**
     * @param bookmark
     * This method will convert Bookmark to Json Object
     * @return JSONObject
     */
    @SuppressWarnings(Constant.UNCHECKED)
    public static JSONObject getBookmarkJSON(Bookmark bookmark) {
        JSONObject bookmarksResult = new JSONObject();
        bookmarksResult.put("success", Boolean.TRUE);
        bookmarksResult.put("bookmarksId", bookmark.getBookmarksID());
        bookmarksResult.put("linkName", bookmark.getLinkName());
        bookmarksResult.put("state", bookmark.getState());
        bookmarksResult.put("chartName", bookmark.getChartName());
        bookmarksResult.put("userId", bookmark.getUserID());
        bookmarksResult.put("timeStamp", bookmark.getTimeStamp());
        bookmarksResult.put("privateFlag", bookmark.isPrivateFlag());
        return bookmarksResult;
    }

    /**
     * @param date
     * @return String
     */
     public static String formatDate(Date date){
         SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
         return dmyFormat.format(date);
     }
     
     /**
      * @param date
      * @return Date
      * @throws ParseException
      */
     public static Date getDate(String date) throws ParseException
     {
         SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            return sdf.parse(date);
     }
     

    public static String dencryptPassword(String keyStorePassword){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        Properties properties = new EncryptableProperties(textEncryptor);
        textEncryptor.setPassword("abcd456");
        properties.setProperty(Constant.KEYSTOREPASS, keyStorePassword);
        String decryptedPassword = properties.getProperty(Constant.KEYSTOREPASS);
        LOG.info("Decrypt Password -- {}", decryptedPassword);
        return decryptedPassword;
    }
     /**
      * @param bookmarksId
      * @return byte[]
      */
     public byte[] encodeBookmarksId(BigInteger bookmarksId){
    	 return Base64.encodeInteger(bookmarksId);
     }
     
     /**
      * @param bookmarksId
      * @return BigInteger
      */
     public BigInteger dencodeBookmarksId(byte[] bookmarksId){
    	 return Base64.decodeInteger(bookmarksId);
     }
     
     /**
  	 * This method used to get json object of the ticket.
  	 * 
  	 * @param ticket
  	 * @return
  	 */
  	@SuppressWarnings("unchecked")
  	public static JSONObject getJsonTicket(String ticket) {
  		JSONObject alertsJson = new JSONObject();
  		alertsJson.put("AuthTicket", ticket);
  		return alertsJson;
  	}
     
}
