package us.deloitteinnovation.polaris.role.model;

/**
 * Created by rbentaarit on 2/27/2017.
 */
public class Role {

    private Integer id;

    private String name;

    //impl
    public Role() {
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
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
