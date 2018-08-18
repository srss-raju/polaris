package us.deloitteinnovation.polaris.alerts.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import us.deloitteinnovation.polaris.alerts.model.AlertsVO;

/**
 * This class creates the ChartsVO with values and returns
 * @author RajeshKumar B
 *
 */
public class AlertsMapper implements RowMapper<AlertsVO> {
    @Override
    public AlertsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	AlertsVO alertsVO = new AlertsVO();
    	alertsVO.setMetric(rs.getString("Metric"));
    	alertsVO.setTimePeriod(rs.getString("Time_Period"));
    	alertsVO.setCustomerChannel(rs.getString("Customer_Level_1"));
    	alertsVO.setCustomerName(rs.getString("Customer_Name"));
    	alertsVO.setAnswer(rs.getString("Answer"));
    	alertsVO.setValue(rs.getString("Value"));
        return alertsVO;
    }
}
