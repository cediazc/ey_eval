package com.eval.cediaz.evaljava.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

public class UserDomain {

    private String id;
    @NotEmpty(message = "Debe ingresar name")
    private String name;
    @Email(message = "El email debe tener el formato xxxxx@xxx.xx")
    @NotNull
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).+$",
            message = "El password debe contener al menos una Mayúscula, letras minúsculas, y a lo menos dos números")
    private String password;

    @NotEmpty
    private List<PhoneDomain> phoneDomains;

    private Date created;

    private Date lastModified;

    private Date lastLogin;

    private String token;

    private Boolean isActive;

    public UserDomain() {
    }

    public UserDomain(String uuid, String name, String email, String password, List<PhoneDomain> phoneDomains) {
        this.id = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneDomains = phoneDomains;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDomain> getPhones() {
        return phoneDomains;
    }

    public void setPhones(List<PhoneDomain> phoneDomains) {
        this.phoneDomains = phoneDomains;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phones=" + phoneDomains +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", lastLogin=" + lastLogin +
                ", token='" + token + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
