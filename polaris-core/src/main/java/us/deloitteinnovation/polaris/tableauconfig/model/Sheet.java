package us.deloitteinnovation.polaris.tableauconfig.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by rbentaarit on 4/27/2017.
 */
public class Sheet {
    private Integer id;
    @NotNull
    @Min(1)
    private Integer tableauId;
    @NotEmpty
    private String name;
    private List<Group> groups;

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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
