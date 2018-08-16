package com.z0976190100.restingnashorn.persistence.entity;

public class ClientScript {

    private int id;
    private int priority;
    private String tapestry;
    private ScriptStage stage;


    public ClientScript(String tapestry){
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

    public ScriptStage getStage() {
        return stage;
    }

    public void setStage(ScriptStage stage) {
        this.stage = stage;
    }
}
