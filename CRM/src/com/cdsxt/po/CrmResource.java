package com.cdsxt.po;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CrmResource {

    @NotNull(message = "常量不能为空")
    @Length(min = 1, max = 6, message = "长度必须在 1~6 位之间")
    private String constant;

    private int enabled;
    private String href;
    private int id;
    private String name;

    private int parent;

    private int shown;
    private String target;
    private String title;
    private int type;

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getShown() {
        return shown;
    }

    public void setShown(int shown) {
        this.shown = shown;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
