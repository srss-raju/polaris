package us.deloitteinnovation.polaris.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionVO implements Serializable{
    
    private static final long serialVersionUID = -3403772780998357987L;

    private String dn;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String firstTimeLogin;
    private String applications;
    private boolean isSysAdmin;
    private boolean isAppAdmin;
    private List<String> applicationRoles = new ArrayList<>();

    public List<String> getApplicationRoles() {
        return this.applicationRoles;
    }

    public void setApplicationRoles(List<String> applicationRoles) {
        this.applicationRoles = applicationRoles;
    }

    public boolean isAppAdmin() {
        return this.isAppAdmin;
    }

    public void setAppAdmin(boolean isAppAdmin) {
        this.isAppAdmin = isAppAdmin;
    }

    public boolean isSysAdmin() {
        return this.isSysAdmin;
    }

    public void setSysAdmin(boolean isSysAdmin) {
        this.isSysAdmin = isSysAdmin;
    }

    public String getApplications() {
        return this.applications;
    }

    public void setApplications(String applications) {
        this.applications = applications;
    }

    public String getDn() {
        return this.dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstTimeLogin() {
        return this.firstTimeLogin;
    }

    public void setFirstTimeLogin(String firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }
}
