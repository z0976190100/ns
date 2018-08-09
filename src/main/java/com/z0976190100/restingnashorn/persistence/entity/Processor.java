package com.z0976190100.restingnashorn.persistence.entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Processor implements Runnable {

    private long id;
    private final ScriptEngine engine;
    private final UserScript userScript;
    private ProcessorState processorState;

    public Processor(UserScript userScript, String engineType, long id) {
        processorState = new ProcessorState(id);
        this.id = id;
        this.engine = new ScriptEngineManager().getEngineByName(engineType);
        this.userScript = userScript;
    }

    @Override
    public void run() {

        try {
            String scriptTapestry = userScript.getTapestry();
            Object result = String.valueOf(engine.eval(scriptTapestry));
            processorState.setResult(result);
        } catch (ScriptException e) {
            processorState.log(e.getMessage());
            e.printStackTrace();
        }
    }

    public ProcessorState getProcessorState() {
        return processorState;
    }
}
