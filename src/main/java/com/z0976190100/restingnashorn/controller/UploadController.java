package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.service.RegistrationService;
import com.z0976190100.restingnashorn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <code>UploadController</code> is responsible for proceeding http requests
 * for uploading script from request body to proceed it with
 * Nashorn engine then.
 * Client-provided script is a parameter of POST request body.
 * For clear, convenient and controllable management of script lifecycle in app,
 * registration and queuing is provided.
 * For managing of script it got to have <code>id</code>,
 * which will be defined in <code>RegistrationService</code>
 **/

//TODO: registration of script
//TODO: queueing of script - scriptsToProceed and scriptsProceeding sets - no priority, LILO
//TODO: prioritized queuing - based on client defined priority
//TODO:
@Controller
public class UploadController {

    private UploadService uploadService;

    @Autowired
    UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/script")
    public String uploadClientScript(@RequestParam(name = "script", defaultValue = "return 0;") String ascript) {

        ClientScript clientScript = uploadService.buildScript(ascript);
        uploadService.registerScript(clientScript);

        // UserScript userScript = new UserScript(auserScript, id);
        //ProcessorState processorState = engineDispatcherService.runEval(userScript);

        return "forward:/script/eval/" + clientScript.getId();
    }


}
