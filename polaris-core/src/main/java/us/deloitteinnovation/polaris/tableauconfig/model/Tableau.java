package us.deloitteinnovation.polaris.tableauconfig.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by rbentaarit on 4/27/2017.
 */
public class Tableau {
    private Integer id;
    @NotEmpty
    private String link;
    @NotEmpty
    private String name;
    private List<Sheet> sheets;
    private List<Parameter> parameters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
