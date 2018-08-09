package com.z0976190100.restingnashorn.service;

import org.springframework.stereotype.Component;

import javax.script.*;

@Component
public class EvaluationService {

    public String consumeScript(String userScript) {
        return scriptEvaluator(userScript);
    }

    private String scriptEvaluator(String userScript) {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        String script =
                "var greeting ='hello world';" +
                        "print(greeting);" +
                        "var fucking = 'fucking';" +
                        "print(greeting);" +
                        "print(fucking);" +
                        "fucking";

        try {

            Compilable comp = (Compilable) engine;

            CompiledScript cs = comp.compile(userScript);


//           script = modifyScript(script,"print");

            Object result = cs.eval();
            return String.valueOf(result);

        } catch (ScriptException e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }

    }
}


