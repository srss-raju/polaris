package us.deloitteinnovation.polaris.kpi.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.kpi.dao.ICustomerKPIDAO;
import us.deloitteinnovation.polaris.kpi.model.KpiCustomer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbentaarit on 12/1/2016.
 */
@Repository
public class CustomerKPIDAOImpl implements ICustomerKPIDAO {
    private static final String GET_CUSTOMERS_NAME  = "SELECT  [ID], [Customer_Name] FROM [tbl_Customer_Master]";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerKPIDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<KpiCustomer> getNames() {

        return jdbcTemplate.query(GET_CUSTOMERS_NAME, rs -> {
            List<KpiCustomer> res = new ArrayList<>();
            while (rs.next()){
                res.add(new KpiCustomer(rs.getInt("ID"), rs.getString("Customer_Name")));
            }
            return res;
        });
    }
}
