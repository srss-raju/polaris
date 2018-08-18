package us.deloitteinnovation.polaris.tableauconfig.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by rbentaarit on 4/27/2017.
 */
public class Filter {
    private Integer id;
    @NotNull
    @Min(1)
    private Integer groupId;
    @NotNull
    private Integer config_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getConfig_id() {
        return config_id;
    }

    public void setConfig_id(Integer config_id) {
        this.config_id = config_id;
    }
}
