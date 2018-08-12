package com.z0976190100.restingnashorn.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ProcessorState {

    private int processorId;
    private List<String> consoleLog = new ArrayList<>();
    private Object result;
    private boolean evalDone;

    public ProcessorState() {
    }

    public ProcessorState(int processorId) {
        this.processorId = processorId;
    }

    public void log(Object o){
        consoleLog.add(String.valueOf(o));
    }

    public long getProcessorId() {
        return processorId;
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

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public boolean isEvalDone() {
        return evalDone;
    }

    public void setEvalDone(boolean evalDone) {
        this.evalDone = evalDone;
    }
}
