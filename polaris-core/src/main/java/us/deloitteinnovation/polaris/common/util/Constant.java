package us.deloitteinnovation.polaris.common.util;

/**
 * Constant Class
 *
 * @author RajeshKumar B
 * @since 9/14/2015
 */
public final class Constant {

   
    /*JPA Configuration*/

    public static final int MAXPOOLSIZE = 30;

    public static final int KEEPALIVESECONDS = 60;
    public static final int PERCENTAGE = 100;
    public static final int POLICY = 3;
    public static final int MAXINTERVAL = 60000;
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String RESPONSE_CONTENT_TYPE = "application/json";
    public static final String CONSTANT_VALUE = "-1";
    public static final String UTF8 = "UTF-8";
    public static final String METRIC = "metric";
    public static final String TIMEPERIOD = "time_period";
    public static final String CUSTOMERCHANNEL = "customer_channel";
    public static final String CUSTOMERNAME = "customer_name";
    public static final String ANSWER = "answer";
    public static final String VALUE = "value";
    public static final String KEYSTOREPASS  = "keystorepass";
    public static final String TACTIC1 = "tactic1";
    public static final String TACTIC2 = "tactic2";
    public static final String TACTIC3 = "tactic3";
    public static final String TACTIC4 = "tactic4";
    public static final String TACTIC5 = "tactic5";
    public static final String TACTIC6 = "tactic6";
    public static final String TACTIC7 = "tactic7";
    public static final String TACTIC8 = "tactic8";
    public static final String UNCHECKED = "unchecked";
    public static final String USER_NAME = "polarisqa@deloitte.com";
    /**
     * SQL Query for Get All Alert Details
     **/

    public static final String SQL_GET_ALL_ALERT_DETAILS = "SELECT TOP 10 [Metric],[Time_Period],[Customer_Level_1],[Customer_Name],[Answer],[Value] FROM [app_Alerts] ";

    /**
     * SQL Query for Get Customer Details
     **/
    public static final String SQL_GET_ALERTS_CUSTOMER_DETAILS = "SELECT [Metric],[Time_Period],[Customer_Level_1],[Customer_Name],[Answer],[Value] FROM [app_Alerts]  where Customer_Name = ? ";

    /**
     * SQL Query for Get Channel Details
     **/
    public static final String SQL_GET_ALERTS_CHANNEL_DETAILS = "SELECT [Metric],[Time_Period],[Customer_Level_1],[Customer_Name],[Answer],[Value] FROM [app_Alerts] where Customer_Level_1 = ? ";

    /**
     * SQL Query which gets the bookmarks
     **/



    /**
     * SQL Query to deleting the bookmarks
     */
    
    public static final String SQL_DELETE_BOOKMARK = "DELETE FROM app_Bookmarks WHERE BOOKMARK_ID IN (:deleteUserBookmarks)";
    
    /**
     * SQL Query which get the promotion and coefficient values
     **/
    public static final String SQL_GET_COEFFICIENT_AND_PROMOTION = "SELECT CV.[Product_ID], CV.[Customer_ID], CV.[Tactic_Coupon], CV.[Tactic_TV], CV.[Tactic_Display],CV.[Depth], CV.[Days_Difference], CV.[Tactic_1], CV.[Tactic_2], CV.[Tactic_3], CV.[Tactic_4], CV.[Tactic_5], CV.[Tactic_6], CV.[Tactic_7], CV.[Tactic_8], PR.[ID], PR.[Promo_Campaign_ID], PR.[Promo_Start_Date], PR.[Promo_End_Date], PR.[Promo_Type], PR.[Promo_Tactic], PR.[Promo_Planned_Units], PR.[Promo_Price], PR.[Promo_Baseline_Units], PR.[Promo_Actual_Units], PR.[Promo_Actual_Cost], PR.[Promo_Planned_Cost], PR.[Promo_Spend_Type], PR.[Sim_Promo_Start_Date], PR.[Sim_Promo_End_Date], PR.[Sim_Promo_Tactic], PR.[Sim_Promo_Units], PR.[Sim_Promo_Price], PR.[Simulated], PR.[Sim_Volume_Lift_Coeffecient], PM.[Product_List_Price] FROM [tbl_Promo] PR INNER JOIN [tbl_Coefficient_Values] CV ON  CV.Product_ID = PR.Product_ID AND CV.Promo_ID = PR.ID  INNER JOIN [tbl_Product_Master] PM ON PR.Product_ID = PM.ID  WHERE CV.Product_ID = ? AND CV.Customer_ID = ? AND PR.ID = ?";

    /**
     * SQL Query which saves the event optimizer details
     **/
    public static final String SQL_SAVE_EVENT_OPTIM_PROMO_DETAILS = "UPDATE [tbl_Promo] SET Sim_Promo_End_Date=?,Sim_Promo_Start_Date=?,Sim_Promo_Tactic=?,Sim_Promo_Units=?,Sim_Volume_Lift_Coeffecient=?, Sim_Promo_Price=?, Simulated=? WHERE Product_ID = ? AND ID = ?";

    /**
     * SQL Query which loads the price evaluator details
     **/
  public static final String SQL_GET_PRICE_EVAL_DETAILS1 = "SELECT [External_Sales_ID],[Customer_ID],[Customer_Name],[Product_Attribute_1],[Product_Level_3],[Product_Name],[Product_ID],[Product_Level_1],[Product_Level_2],[Product_Level_4],[Product_Level_5],[Product_Attribute_2],[Product_Attribute_3],[Product_Attribute_4],[Product_Attribute_5],[POS_Week_End_Date],[POS_Actual_Units],[Gross_Revenue],[Sim_Shelf_Price],[Sim_Value],[Sim_Units],[POS_Actual_Average_Price],[POS_Actual_Promo_Value],[POS_Actual_Non_Promo_Value],[POS_Actual_Promo_Units],[POS_Actual_Non_Promo_Units],[POS_Baseline_Promo_Units], [Gross_Revenue] AS  [Coefficient_Value] FROM [tbl_Price_Evaluator] " ;
  public static final String SQL_GET_PRICE_EVAL_DETAILS2 = "WHERE Customer_ID = ? AND ";
  public static final String SQL_GET_PRICE_EVAL_DETAILS3 = " GROUP BY Product_Level_1,Product_Name,Customer_ID,Customer_Name,Product_ID,Product_Level_2,Product_Level_3,Product_Level_4,Product_Level_5,Product_Attribute_1,Product_Attribute_2,Product_Attribute_3,Product_Attribute_4,Product_Attribute_5,POS_Week_End_Date,POS_Actual_Units,Gross_Revenue,Sim_Shelf_Price,Sim_Value,Sim_Units,POS_Actual_Average_Price,External_Sales_ID,POS_Actual_Promo_Value,POS_Actual_Non_Promo_Value,POS_Actual_Promo_Units,POS_Actual_Non_Promo_Units,POS_Baseline_Promo_Units ";
  public static final String SQL_GET_PRICE_EVAL_DETAILS4 = " ORDER BY Customer_ID, ";
  public static final String SQL_GET_PRICE_EVAL_DETAILS5 = " Product_Name";


    public static final String PRODUCTATTRIBUTE1 = "Product_Attribute_1";
    public static final String PRODUCTATTRIBUTE2 = "Product_Attribute_2";
    public static final String PRODUCTATTRIBUTE3 = "Product_Attribute_3";
    public static final String PRODUCTATTRIBUTE4 = "Product_Attribute_4";
    public static final String PRODUCTATTRIBUTE5 = "Product_Attribute_5";
    public static final String OFF_SET = "startRowId";
    public static final String PAGE_SIZE = "endRowId";

    public static final String SEARCH_TEXT = "SEARCH_TEXT";

    /**
     * SQL Query which saves the event optimizer details
     **/
  public static final String SQL_SAVE_PRICE_EVAL_DETAILS = "UPDATE [tbl_Price_Evaluator ] SET Sim_Shelf_Price=?, Sim_Units=? , Sim_Value =?  WHERE [External_Sales_ID]  = ?";

    public static final String GET_CUSTOMERS = "SELECT DISTINCT [ID],[Customer_Name] FROM [tbl_Customer_Master]";

    public static final String SQL_GET_EXTERNAL_SALES_AND_COEFFICIENTS = "SELECT ES.ID AS External_Sales_ID, ES.Product_ID, ' ' AS Product_Name, ES.Customer_ID, ES.POS_Week_End_Date,ES.POS_Baseline_Promo_Units, ES.POS_Actual_Non_Promo_Units, ES.POS_Actual_Promo_Units, ES.POS_Actual_Non_Promo_Value, ES.POS_Actual_Promo_Value, ES.Sim_Value, ES.Shelf_Price, ES.Sim_Shelf_Price, ES.Sim_Units, ' ' AS Customer_Name,' ' AS Product_Level_1, ' ' AS Product_Level_2, ' ' AS Product_Level_3, ' ' AS Product_Level_4, ' ' AS Product_Level_5, ' ' AS Product_Attribute_1, ' ' AS Product_Attribute_2, ' ' AS Product_Attribute_3, ' ' AS Product_Attribute_4, ' ' AS Product_Attribute_5, POS_Actual_Non_Promo_Value AS POS_Actual_Average_Price,POS_Actual_Promo_Units AS Gross_Revenue,COEFF.Coefficient_Value AS Coefficient_Value FROM tbl_External_Sales ES INNER JOIN tbl_Price_Elasticity_Coefficients COEFF ON  ES.PRODUCT_ID=COEFF.Comparable_Product_ID WHERE ES.CUSTOMER_ID=:customerId AND COEFF.CUSTOMER_ID=:customerId AND COEFF.Product_ID IN (:simulatedProductIds) ORDER BY ES.PRODUCT_ID";
    
    public static final String SQL_GET_MENU_DETAILS = "SELECT ID, Module, Main_Question, Main_Question_Index, Sub_Question, Sub_Question_Index, URL FROM dbo.app_Decision_Workflow  ORDER BY ID";

    public static final Integer DEFAULT_OFFSET = 1 ;
    public static final Integer DEFAULT_SIZE = 10;

    private Constant() {
    }
}
