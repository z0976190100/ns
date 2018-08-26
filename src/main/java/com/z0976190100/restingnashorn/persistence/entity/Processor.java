package com.z0976190100.restingnashorn.persistence.entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.util.concurrent.Future;

import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.AFTER_EVALUATION;
import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.ERROR_OF_EVALUATION;
import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.PROCESSING_EVALUATION;


public class Processor implements Runnable {

    //TODO: hash and equals

    private int id;
    private ScriptEngine engine;
    private ClientScript clientScript;
    private ProcessorState processorState;
    private Future task;
    private Thread thread;



    public Processor(ClientScript clientScript, String engineType) {
        this.id = clientScript.getId();
        this.processorState = new ProcessorState(id);
        this.engine = new ScriptEngineManager().getEngineByName(engineType);
        this.clientScript = clientScript;
    }

    @Override
    public void run() {

        try {
            thread = Thread.currentThread();
            String scriptTapestry = modifyScriptTapestry(clientScript.getTapestry(), "print");
            engine.put("console", processorState);
            processorState.setScriptStage(PROCESSING_EVALUATION);
            Object result = String.valueOf(engine.eval(scriptTapestry));
            processorState.setScriptStage(AFTER_EVALUATION);
            processorState.setResult(result);
            processorState.setEvalDone(true);
//            doNotifyAll();
        } catch (ScriptException e) {
            processorState.setScriptStage(ERROR_OF_EVALUATION);
            processorState.log(e.getMessage());
            processorState.setEvalDone(true);
            e.printStackTrace();
            // just for fun
        } catch (ThreadDeath e) {
            System.err.println("------- OUCH!!!------");
            throw new ThreadDeath();
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
        int result = getId();
        result = 31 * result + getEngine().hashCode();
        result = 31 * result + getClientScript().hashCode();
        result = 31 * result + getProcessorState().hashCode();
        result = 31 * result + (getTask() != null ? getTask().hashCode() : 0);
        result = 31 * result + (getThread() != null ? getThread().hashCode() : 0);
        return result;
    }
}
