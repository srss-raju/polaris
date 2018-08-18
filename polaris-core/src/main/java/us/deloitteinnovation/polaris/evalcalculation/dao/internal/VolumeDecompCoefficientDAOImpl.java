package us.deloitteinnovation.polaris.evalcalculation.dao.internal;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.evalcalculation.dao.IVolumeDecompCoefficientDAO;
import us.deloitteinnovation.polaris.evalcalculation.exception.CoefficientNotFoundException;
import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompCoefficient;

import java.util.List;

/**
 * Created by rbentaarit on 8/31/2016.
 */
@Repository
public class VolumeDecompCoefficientDAOImpl implements IVolumeDecompCoefficientDAO {
    
    private static final String SELECT_TOP_VOLUME_DECOMP_COEFFICIENT = "SELECT TOP 1 [CPP] " +
            "      ,[CNPP] " +
            "      ,[CPV1] " +
            "      ,[CPV2] " +
            "      ,[CPV3] " +
            "      ,[CPV4] " +
            "      ,[CPV5] " +
            "      ,[CNPV1] " +
            "      ,[CNPV2] " +
            "      ,[CNPV3] " +
            "      ,[CNPV4] " +
            "      ,[CNPV5] " +
            "  FROM [dbo].[app_Volume_Decomp_Coefficient]";


    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public VolumeDecompCoefficientDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public VolumeDecompCoefficient findFirst() {
        List<VolumeDecompCoefficient> query = jdbcTemplate.query(SELECT_TOP_VOLUME_DECOMP_COEFFICIENT, new Object[]{}, new BeanPropertyRowMapper<>(VolumeDecompCoefficient.class));

        if (CollectionUtils.isEmpty(query)) {
            throw new CoefficientNotFoundException();
        }
        return query.get(0);
    }
}
