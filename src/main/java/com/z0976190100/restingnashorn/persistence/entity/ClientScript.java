package com.z0976190100.restingnashorn.persistence.entity;

import org.springframework.stereotype.Component;

@Component
public class ClientScript {

    private int id;
    private int priority;
    private String tapestry;


    public ClientScript() {
    }

    public ClientScript(String tapestry, int id){
        this.tapestry = tapestry;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
