package com.z0976190100.restingnashorn.persistence.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class encapsulates information
 * about <code>Script</code> and <code>Processor</code>
 * on which script runs.
 *
 *
 *
 *
 */
public class ProcessorState {

    private int processingScriptId;
    private ScriptStage scriptStage;
    private List<String> scriptConsoleLog = new ArrayList<>();
    private Object result;
    private boolean evalDone;


    public ProcessorState(int processingScriptId) {
        this.processingScriptId = processingScriptId;
    }

    public void log(Object o){
        scriptConsoleLog.add(String.valueOf(o));
    }

    public long getProcessingScriptId() {
        return processingScriptId;
    }

    public List<String> getScriptConsoleLog() {
        return scriptConsoleLog;
    }

    public void setScriptConsoleLog(List<String> scriptConsoleLog) {
        this.scriptConsoleLog = scriptConsoleLog;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setProcessingScriptId(int processingScriptId) {
        this.processingScriptId = processingScriptId;
    }

    public boolean isEvalDone() {
        return evalDone;
    }

    public void setEvalDone(boolean evalDone) {
        this.evalDone = evalDone;
    }

    public ScriptStage getScriptStage() {
        return scriptStage;
    }

    public void setScriptStage(ScriptStage scriptStage) {
        this.scriptStage = scriptStage;
    }
}
