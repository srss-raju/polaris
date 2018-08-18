package us.deloitteinnovation.polaris.evalcalculation.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.evalcalculation.dao.IVolumeDecompDetailDAO;
import us.deloitteinnovation.polaris.evalcalculation.exception.PromotionNotFoundException;
import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rbentaarit on 8/31/2016.
 */
@Repository
public class VolumeDecompDetailsDAOImpl implements IVolumeDecompDetailDAO {

 private static final String SELECT_VOLUME_DECOMP_DETAILS = "SELECT [Id] " +
         "      ,[PromoType] " +
         "      ,[PromoTactic] " +
         "      ,[CustomerId] " +
         "      ,[ProductId] " +
         "      ,[Date] " +
         "      ,[PromoPrice] " +
         "      ,[NonPromoPrice] " +
         "      ,[CP] " +
         "      ,[CNP] " +
         "      ,[PV1] " +
         "      ,[PV2] " +
         "      ,[PV3] " +
         "      ,[PV4] " +
         "      ,[PV5] " +
         "      ,[NPV1] " +
         "      ,[NPV2] " +
         "      ,[NPV3] " +
         "      ,[NPV4] " +
         "      ,[NPV5] " +
         "      ,[V] " +
         "  FROM [dbo].[app_Volume_Decomp_Detail] " +
         "WHERE [CustomerId]=? and [ProductId]=? and [PromoTactic]=? and [PromoType]=? ORDER BY ABS(DATEDIFF(day,[Date],?))%365";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VolumeDecompDetailsDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public VolumeDecompDetail findByPromotion(int customerId, int productId, String tactic, String type, String date) {
         VolumeDecompDetail volumeDetail =  jdbcTemplate.query(SELECT_VOLUME_DECOMP_DETAILS, new Object[]{customerId, productId, tactic, type, date}, rs -> rs.next() ? extractVolumeDecompDetail(rs) : null);
        if  (volumeDetail == null){
            throw new PromotionNotFoundException();
        }
        return volumeDetail;
    }

    private VolumeDecompDetail extractVolumeDecompDetail(ResultSet resultSet) throws SQLException {
        VolumeDecompDetail volumeDecompDetail = new VolumeDecompDetail();
        volumeDecompDetail.setDate(resultSet.getDate("Date"));
        volumeDecompDetail.setCustomerId(resultSet.getInt("CustomerId"));
        volumeDecompDetail.setPromoType(resultSet.getString("PromoType"));
        volumeDecompDetail.setPromoTactic(resultSet.getString("PromoTactic"));
        volumeDecompDetail.setPromoPrice(resultSet.getFloat("PromoPrice"));
        volumeDecompDetail.setNonPromoPrice(resultSet.getFloat("NonPromoPrice"));
        volumeDecompDetail.setCp(resultSet.getBoolean("CP"));
        volumeDecompDetail.setCnp(resultSet.getBoolean("CNP"));
        volumeDecompDetail.setPv1(resultSet.getBoolean("PV1"));
        volumeDecompDetail.setPv2(resultSet.getBoolean("PV2"));
        volumeDecompDetail.setPv3(resultSet.getBoolean("PV3"));
        volumeDecompDetail.setPv4(resultSet.getBoolean("PV4"));
        volumeDecompDetail.setPv5(resultSet.getBoolean("PV5"));
        volumeDecompDetail.setNpv1(resultSet.getBoolean("NPV1"));
        volumeDecompDetail.setNpv2(resultSet.getBoolean("NPV2"));
        volumeDecompDetail.setNpv3(resultSet.getBoolean("NPV3"));
        volumeDecompDetail.setNpv4(resultSet.getBoolean("NPV4"));
        volumeDecompDetail.setNpv5(resultSet.getBoolean("NPV5"));
        volumeDecompDetail.setV(resultSet.getFloat("V"));
        return volumeDecompDetail;
    }
}
