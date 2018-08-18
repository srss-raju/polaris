package us.deloitteinnovation.polaris.alerts.service.internal;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.alerts.dao.IAlertsDAO;
import us.deloitteinnovation.polaris.alerts.service.IAlertsService;
import us.deloitteinnovation.polaris.common.util.Constant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * 
 * @author RajeshKumar B
 *
 */
@Service("alertsService")
public class AlertsServiceImpl implements IAlertsService  {

    private static final Logger LOG = LoggerFactory.getLogger(AlertsServiceImpl.class);

    private IAlertsDAO alertsDAO;

    @Value("${polaris.tableauServer}")
    private String tableauServer;

    /**
     * @param alertsDAO
     */
    @Autowired
    public AlertsServiceImpl(IAlertsDAO alertsDAO) {
        this.alertsDAO = alertsDAO;
    }
    
    @Override
    public JSONObject getAlertsDetail() {
        LOG.info("------getAlertsDetail---------");
        return alertsDAO.getAlertsDetail();
    }

    @Override
    public JSONObject getCustomerDetails(String customerName) {
        return alertsDAO.getCustomerDetails(customerName);
    }

    @Override
    public JSONObject getChannelDetails(String channelName) {
        return alertsDAO.getChannelDetails(channelName);
    }
    
    @Override
	public String getTrustedTicket(String user, String targetSite) {
        StringBuilder  rsp = new StringBuilder();

        try {
            StringBuilder data = new StringBuilder();
            data.append(URLEncoder.encode("username", Constant.UTF8));
            data.append("=");
            data.append(URLEncoder.encode(user, Constant.UTF8));
            if(!StringUtils.isEmpty(targetSite)){
            	data.append("&");
            	data.append(URLEncoder.encode("target_site", Constant.UTF8));
            	data.append("=");
            	data.append(URLEncoder.encode(targetSite, Constant.UTF8));
            }

            // Send the request
            URL url = new URL("http://" + tableauServer + "/trusted");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
                out.write(data.toString());
                out.flush();

                String line;
                while ( (line = in.readLine()) != null) {
                    rsp.append(line);
                }
            } catch(Exception exception){
                LOG.error("Exception occurred in getTrustedTicket at BufferedReader", exception);
            }
        } catch (Exception e) {
			LOG.error("Exception caught in getTrustedTicket ", e);
		}
        return rsp.toString();
	}
    
}
