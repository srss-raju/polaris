package us.deloitteinnovation.polaris.tableauconfig.dao.internal;

import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.tableauconfig.dao.ISheetConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Sheet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Repository
public class SheetConfigDAOImpl extends AbstractDAO implements ISheetConfigDAO {

    private final static String GET_ALL_SHEETS = "SELECT [Id],[name],[tableau_config_Id] FROM [dbo].[app_Sheet_Config]";
    private final static String GET_SHEET_BY_ID = "SELECT [Id],[name],[tableau_config_Id] FROM [dbo].[app_Sheet_Config] WHERE [Id]= :sheetId";
    private final static String GET_SHEET_BY_TABLEAU_CONFIG = "SELECT [Id],[name],[tableau_config_Id] FROM [dbo].[app_Sheet_Config] WHERE [tableau_config_Id]= :tableauId";
    private final static String INSERT_SHEET = "INSERT INTO [dbo].[app_Sheet_Config] ([name],[tableau_config_Id]) OUTPUT Inserted.id VALUES (:name, :tableauId)";

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Sheet_Config");    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");    }

    @Override
    public List<Sheet> getAllSheets() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_SHEETS, rs -> {
            List<Sheet> sheets = new ArrayList<>();
            while (rs.next()){
                sheets.add(extractSheetFromResultSet(rs));
            }
            return sheets;
        });
    }

    @Override
    public Sheet getSheetById(Integer sheetId) {
        return getNamedParameterJdbcTemplate().query(GET_SHEET_BY_ID, Collections.singletonMap("sheetId", sheetId), rs ->  rs.next() ? extractSheetFromResultSet(rs) : null);
    }

    @Override
    public List<Sheet> getSheetsByTableauConfig(Integer tableauConfigId) {
        return getNamedParameterJdbcTemplate().query(GET_SHEET_BY_TABLEAU_CONFIG, Collections.singletonMap("tableauId", tableauConfigId), (ResultSet rs) -> {
            List<Sheet> sheets = new ArrayList<>();
            while (rs.next()){
                 sheets.add(extractSheetFromResultSet(rs));
            }
            return sheets;
        });
    }

    @Override
    public Integer insertSheet(Sheet sheet) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name",sheet.getName());
        paramMap.put("tableauId",sheet.getTableauId());
        return getNamedParameterJdbcTemplate().queryForObject(INSERT_SHEET, paramMap, Integer.class);
    }

    private Sheet extractSheetFromResultSet(ResultSet rs) throws SQLException {
        Sheet sheet = new Sheet();
        sheet.setId(rs.getInt("Id"));
        sheet.setName(rs.getString("name"));
        sheet.setTableauId(rs.getInt("tableau_config_Id"));
        return sheet;
    }
}
