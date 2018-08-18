package us.deloitteinnovation.polaris.alerts.dao.internal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import us.deloitteinnovation.polaris.alerts.dao.IAlertsDAO;
import us.deloitteinnovation.polaris.alerts.model.AlertsVO;
import us.deloitteinnovation.polaris.alerts.utils.AlertsMapper;
import us.deloitteinnovation.polaris.common.util.Constant;
import us.deloitteinnovation.polaris.common.util.PolarisUtil;

/**
 * 
 * @author RajeshKumar B
 *
 */
@Repository
public class AlertsDAOImpl implements IAlertsDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AlertsDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject getAlertsDetail(){
        JSONArray alertJsonArray = new JSONArray();
        JSONObject allalertsJsonObj = new JSONObject();

        List <AlertsVO> alertsVOList = jdbcTemplate.query(Constant.SQL_GET_ALL_ALERT_DETAILS, new AlertsMapper());
        if (alertsVOList != null) {
            alertJsonArray.add(PolarisUtil.getAlertJsonObject(alertsVOList));
            alertJsonArray.add(getAllMetricJsonObj(alertsVOList));
            alertJsonArray.add(getAllTimePeriodJsonObj(alertsVOList));
            allalertsJsonObj.put("allalertDetails", alertJsonArray);
        }
        return allalertsJsonObj;
    }

    @Override
    public JSONObject getCustomerDetails(final String custName) {
        JSONArray alertJsonArray = new JSONArray();
        JSONObject allalertsJsonObj = new JSONObject();
        List<AlertsVO> alertsVOList = jdbcTemplate.query(Constant.SQL_GET_ALERTS_CUSTOMER_DETAILS, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                            preparedStatement.setString(1, custName);
                        }
                    }, new AlertsMapper());

        if (alertsVOList != null) {
            alertJsonArray.add(PolarisUtil.getAlertJsonObject(alertsVOList));
            alertJsonArray.add(getAllMetricJsonObj(alertsVOList));
            alertJsonArray.add(getAllTimePeriodJsonObj(alertsVOList));
            allalertsJsonObj.put("allalertDetails", alertJsonArray);
        }
        return allalertsJsonObj;
    }

    @Override
    public JSONObject getChannelDetails(final String channelName) {
        JSONArray alertJsonArray = new JSONArray();
        JSONObject allalertsJsonObj = new JSONObject();
        List<AlertsVO> alertsVOList = jdbcTemplate.query(Constant.SQL_GET_ALERTS_CHANNEL_DETAILS,new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                            preparedStatement.setString(1, channelName);
                        }
                    }, new AlertsMapper());
        if (alertsVOList != null) {
            alertJsonArray.add(PolarisUtil.getAlertJsonObject(alertsVOList));
            alertJsonArray.add(getAllMetricJsonObj(alertsVOList));
            alertJsonArray.add(getAllTimePeriodJsonObj(alertsVOList));
            allalertsJsonObj.put("allalertDetails", alertJsonArray);
        }
        return allalertsJsonObj;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject getAllMetricJsonObj(List<AlertsVO> alertsVOList) {
        JSONObject metricJsonObj = new JSONObject();
        TreeSet<String> metricSet = new TreeSet<String>();
        JSONArray jsonArray = new JSONArray();

        for (AlertsVO alertsVO : alertsVOList) {
            metricSet.add(alertsVO.getMetric());
        }
        jsonArray.add(metricSet);
        metricJsonObj.put("metric", jsonArray);
        return metricJsonObj;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject getAllTimePeriodJsonObj(List<AlertsVO> alertsVOList) {
        JSONObject timePeriodJsonObj = new JSONObject();
        TreeSet<String> timePeriodSet = new TreeSet<String>();
        for (AlertsVO alertsVO : alertsVOList) {
            timePeriodSet.add(alertsVO.getTimePeriod());
        }
        JSONArray jsonArray = new JSONArray();

        jsonArray.add(timePeriodSet);
        timePeriodJsonObj.put("timePeriod", jsonArray);
        return timePeriodJsonObj;
    }

}
