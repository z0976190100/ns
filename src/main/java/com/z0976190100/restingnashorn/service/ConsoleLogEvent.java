package com.z0976190100.restingnashorn.service;

public class ConsoleLogEvent {

    private WriterHandler writerHandler;

    private String field = "im field";

    public ConsoleLogEvent() {

    }

    public void log(Object o){
        System.out.println("here log invocation goes! " + String.valueOf(o));
    }

    public String getField() {
        return field;
    }
}
