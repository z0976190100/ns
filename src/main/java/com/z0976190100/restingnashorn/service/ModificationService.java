package com.z0976190100.restingnashorn.service;

class ModificationService {

    static String modifyScript(String script, String inCase) {

        String modScript = null;

        switch (inCase) {

            case "print":

                modScript = "var C = Java.type('com.z0976190100.restingnashorn.persistence.entity.ProcessorState');" +
                        "var console = new C();" + script;

                modScript = modScript.replace("print", "console.log");
                break;
            default:
                break;
        }
        return modScript;
    }

}
