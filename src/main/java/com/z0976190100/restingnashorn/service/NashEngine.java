package com.z0976190100.restingnashorn.service;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.io.*;

import static com.z097619100.resting_nashorn.service.ScriptModifier.modifyScript;

public class NashEngine {


    public static void main(String[] args) throws ScriptException {

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            String script =
                    "var greeting ='hello world';" +
                    "print(greeting);" +
                    "var fucking = 'fucking';" +
                    "print(greeting);" +
                    "print(fucking);" +
                            "fucking";
        Compilable comp = (Compilable) engine;

        CompiledScript cs =  comp.compile(script);


//           script = modifyScript(script,"print");

            Object result = cs.eval();

        }
    }


