package com.z0976190100.restingnashorn.persistence.entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Processor implements Runnable {

    private int id;
    private ScriptEngine engine;
    private ClientScript clientScript;
    private ProcessorState processorState;
    private Thread thread;

    public Processor(ClientScript clientScript, String engineType) {
        processorState = new ProcessorState(id);
        this.id = clientScript.getId();
        this.engine = new ScriptEngineManager().getEngineByName(engineType);
        this.clientScript = clientScript;
    }

    @Override
    public void run() {

        try {
            String scriptTapestry = clientScript.getTapestry();
            engine.put("console", processorState);
            Object result = String.valueOf(engine.eval(scriptTapestry));
            processorState.setResult(result);
            processorState.setEvalDone(true);
            notifyAll();
        } catch (ScriptException e) {
            processorState.log(e.getMessage());
            processorState.setEvalDone(true);
            e.printStackTrace();
        }
    }

    public ProcessorState getProcessorState() {
        return processorState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScriptEngine getEngine() {
        return engine;
    }

    public void setEngine(ScriptEngine engine) {
        this.engine = engine;
    }

    public ClientScript getClientScript() {
        return clientScript;
    }

    public void setClientScript(ClientScript clientScript) {
        this.clientScript = clientScript;
    }

    public void setProcessorState(ProcessorState processorState) {
        this.processorState = processorState;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
