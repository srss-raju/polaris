package us.deloitteinnovation.polaris.tableauconfig.dao.internal;

import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.common.dao.AbstractDAO;
import us.deloitteinnovation.polaris.tableauconfig.dao.ITableauConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Tableau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Repository
public class TableauConfigDAOImpl extends AbstractDAO implements ITableauConfigDAO {


    private final static String GET_ALL_TABLEAU = "SELECT [Id],[name],[link] FROM [dbo].[app_Tableau_Config]";
    private final static String GET_TABLEAU_BY_ID = "SELECT [Id],[name],[link] FROM [dbo].[app_Tableau_Config] WHERE [ID] = :tableauId";
    private final static String INSERT_TABLEAU = "INSERT INTO [dbo].[app_Tableau_Config] ([name],[link]) OUTPUT Inserted.id VALUES (:name , :link)";

    @Override
    protected Optional<String> getTable() {
        return Optional.of("app_Tableau_Config");
    }

    @Override
    protected Optional<String> getGeneratedIdColumn() {
        return Optional.of("Id");
    }

    @Override
    public List<Tableau> getAllTableau() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_TABLEAU, rs -> {
           List<Tableau> tableauList = new ArrayList<>();
            while (rs.next()){
               tableauList.add(extractTableauFromResultSet(rs));
            }
            return tableauList;
        });
    }

    @Override
    public Tableau getTableauById(Integer tableauId) {
        return getNamedParameterJdbcTemplate().query(GET_TABLEAU_BY_ID, Collections.singletonMap("tableauId", tableauId), rs ->  rs.next() ? extractTableauFromResultSet(rs) : null);
    }

    @Override
    public Integer insertTableau(Tableau tableau) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("link", tableau.getLink());
        paramMap.put("name", tableau.getName());
        return getNamedParameterJdbcTemplate().queryForObject(INSERT_TABLEAU, paramMap,Integer.class);
    }

    private Tableau extractTableauFromResultSet(ResultSet rs) throws SQLException {
        Tableau tableau = new Tableau();
        tableau.setId(rs.getInt("Id"));
        tableau.setLink(rs.getString("link"));
        tableau.setName(rs.getString("name"));
        return tableau;
    }
}
