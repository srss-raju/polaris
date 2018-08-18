package us.deloitteinnovation.polaris.kpi.model;

/**
 * Created by rbentaarit on 12/1/2016.
 */
public class KpiCustomer {
    private Integer id;
    private String name;

    public KpiCustomer(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
