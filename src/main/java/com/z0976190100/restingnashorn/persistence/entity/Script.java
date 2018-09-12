package com.z0976190100.restingnashorn.persistence.entity;

public class Script {

    private int id;
    private int priority;
    private String tapestry;

    public Script(String tapestry){
        this.tapestry = tapestry;
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
