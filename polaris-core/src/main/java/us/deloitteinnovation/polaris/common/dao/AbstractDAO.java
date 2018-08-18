package us.deloitteinnovation.polaris.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Abstract Data Access Object
 *
 * @author yvinogradov
 * @since 2/8/2017
 */
public abstract class AbstractDAO extends NamedParameterJdbcDaoSupport {

    protected SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDatasource(DataSource dataSource) {
        setDataSource(dataSource);
        Optional<String> table = getTable();
        Optional<String> generatedId = getGeneratedIdColumn();
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        table.ifPresent(tbl -> this.simpleJdbcInsert = this.simpleJdbcInsert.withTableName(tbl));
        generatedId.ifPresent(col -> this.simpleJdbcInsert = this.simpleJdbcInsert.usingGeneratedKeyColumns(col));
    }

    /**
     * Get a Single Connection Datasource
     *
     * @return Single Connection Datasource
     */
    protected SingleConnectionDataSource getSingleConnectionDataSource() {
        try {
            return new SingleConnectionDataSource(getDataSource().getConnection(), true);
        }
        catch (SQLException e) {
            throw new DataAccessResourceFailureException("Unable to create single connection ds", e);
        }
    }

    protected abstract Optional<String> getTable();

    protected abstract Optional<String> getGeneratedIdColumn();

}

