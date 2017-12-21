package com.cdsxt.po;

public class CrmUser {

    private String description;
    private String email;
    private int enabled;
    private int id;
    private int locked;
    private String password;
    private int sex;
    private String username;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CrmUser{" +
                "description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", id=" + id +
                ", locked=" + locked +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", username='" + username + '\'' +
                '}';
    }
}
