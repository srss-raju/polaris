package us.deloitteinnovation.polaris.tableauconfig.model;

/**
 * Created by araju on 7/6/2017.
 */
public class ClientConfig {
    Integer id;
    String tableName;
    String columnName;
    String clientLabel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getClientLabel() {
        return clientLabel;
    }

    public void setClientLabel(String clientLabel) {
        this.clientLabel = clientLabel;
    }

}
