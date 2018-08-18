package us.deloitteinnovation.polaris.tableauconfig.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by rbentaarit on 4/27/2017.
 */
public class Parameter {
    private Integer id;
    @NotNull
    @Min(1)
    private Integer tableauId;
    @NotEmpty
    private String name;

    private Integer config_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableauId() {
        return tableauId;
    }

    public void setTableauId(Integer tableauId) {
        this.tableauId = tableauId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConfig_id() {
        return config_id;
    }

    public void setConfig_id(Integer config_id) {
        this.config_id = config_id;
    }
}
