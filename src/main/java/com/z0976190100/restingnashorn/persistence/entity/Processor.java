package com.z0976190100.restingnashorn.persistence.entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.Future;

import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.*;


public class Processor implements Runnable {

    private int id;
    private ScriptEngine engine;
    private Script script;
    private ProcessorState processorState;
    private Future task;
    private Thread thread;



    public Processor(Script script, String engineType) {
        this.id = script.getId();
        this.processorState = new ProcessorState(id);
        this.engine = new ScriptEngineManager().getEngineByName(engineType);
        this.script = script;
    }

    @Override
    public void run() {

        try {
            thread = Thread.currentThread();
            String scriptTapestry = modifyScriptTapestry(script.getTapestry(), "print");
            engine.put("console", processorState);
            processorState.setScriptStage(PROCESSING_EVALUATION);
            Object result = String.valueOf(engine.eval(scriptTapestry));
            processorState.setScriptStage(AFTER_EVALUATION);
            processorState.setResult(result);
            processorState.setEvalDone(true);
        } catch (ScriptException e) {
            processorState.setScriptStage(ERROR_OF_EVALUATION);
            processorState.log(e.getMessage());
            processorState.setEvalDone(true);
            e.printStackTrace();
        } catch (ThreadDeath e) {
            System.err.println("------- OUCH!!!------");
            throw e;
        }
    }

    private String modifyScriptTapestry(String auserScript, String inCase) {

        String modScript = null;

        switch (inCase) {

            case "print":

                modScript = "var console;" + auserScript;

                modScript = modScript.replace("print(", "console.log(");

                break;
            default:
                break;
        }
        return modScript;
    }

    public synchronized ProcessorState getProcessorState() {
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

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
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

    public Future getTask() {
        return task;
    }

    public void setTask(Future task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processor)) return false;

        Processor processor = (Processor) o;

        return getId() == processor.getId();
    }

    @Override
    public int hashCode() {
        int result = getId() ^ (getId() >>> 32);
        result = 31 * result ;
        return result;
    }
}
