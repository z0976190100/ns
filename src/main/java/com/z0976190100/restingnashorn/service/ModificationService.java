package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import org.springframework.stereotype.Service;

@Service
class ModificationService {

    public ClientScript modifyScript(ClientScript auserScript, String inCase) {

        String userScript = auserScript.getTapestry();
        String modScript = null;

        switch (inCase) {

            case "print":

                modScript = "var console;" + userScript;

                modScript = modScript.replace("print", "console.log");

                auserScript.setTapestry(modScript);
                break;
            default:
                break;
        }
        return auserScript;
    }

}
