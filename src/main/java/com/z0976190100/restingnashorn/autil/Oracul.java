package com.z0976190100.restingnashorn.autil;

import com.z0976190100.restingnashorn.anentity.Aresult;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.Callable;

public class Oracul implements Callable<Aresult> {

    String targetScript;

    public Oracul(String targetScript) {
        this.targetScript = targetScript;
    }

    @Override
    public Aresult call() throws Exception {

        return spitResult();
    }

    public Aresult spitResult(){

        Object aResult = null;
        try {
            aResult = awakeNashorn().eval(targetScript);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return (Aresult)aResult;
    }



    private ScriptEngine awakeNashorn(){

        return new ScriptEngineManager().getEngineByName("nashorn");
    }

}
