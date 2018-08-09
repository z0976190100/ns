package com.z0976190100.restingnashorn.persistence.entity;

import org.springframework.stereotype.Component;

@Component
public class UserScript {

    private long id;
    private int priority;
    private String tapestry;

    public UserScript() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTapestry() {
        return tapestry;
    }

    public void setTapestry(String tapestry) {
        this.tapestry = tapestry;
    }
}
