package com.z0976190100.restingnashorn.service;

class ScriptModifier {

    static String modifyScript(String script, String inCase) {

        String modScript = null;

        switch (inCase) {

            case "print":

                modScript = "var C = Java.type('com.z097619100.resting_nashorn.service.ConsoleLogEvent');" +
                        "var console = new C();" + script;

                modScript = modScript.replace("print", "console.log");
                break;
            default:
                break;
        }
        return modScript;
    }

}
