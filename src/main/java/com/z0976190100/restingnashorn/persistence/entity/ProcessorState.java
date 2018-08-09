package com.z0976190100.restingnashorn.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ProcessorState {

    private long processorId;
    private List<String> consoleLog = new ArrayList<>();
    private Object result ;

    public ProcessorState() {
    }

    public ProcessorState(long processorId) {
        this.processorId = processorId;
    }

    public void log(Object o){
        consoleLog.add(String.valueOf(o));
    }

    public List<String> getConsoleLog() {
        return consoleLog;
    }

    public void setConsoleLog(List<String> consoleLog) {
        this.consoleLog = consoleLog;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
