package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.z0976190100.restingnashorn.util.AppVariables.scriptsToProceed;

/**
 * <code>UploadController</code> is responsible for proceeding http requests
 * for uploading script from request body to proceed it with
 * Nashorn engine then.
 * Client-provided script is a parameter of POST request body.
 * For clear, convenient and controllable management of script lifecycle in app,
 * registration and queuing is provided.
 * For managing of script it got to have <code>id</code>,
 * which will be defined in <code>UploadService</code>
 **/

//TODO: prioritized queuing - based on client-defined priority
//TODO:

@Controller
public class UploadController {

    private UploadService uploadService;
    private ProcessorManagerService processorManagerService;

    @Autowired
    UploadController(UploadService uploadService,
                     ProcessorManagerService processorManagerService) {
        this.uploadService = uploadService;
        this.processorManagerService = processorManagerService;
    }

    @PostMapping("/script")
    public String uploadClientScript(@RequestParam(name = "script") String ascript,
                                     @RequestParam(name = "async", defaultValue = "false") boolean async) {

        ClientScript clientScript = uploadService.buildScript(ascript);
        uploadService.registerScript(clientScript);

        if (async) {
            processorManagerService.launchProcessor(clientScript.getId());
            return "forward:/asyncscript/" + clientScript.getId();
        }

        return "forward:/script/eval/" + clientScript.getId();
    }

    @PostMapping("/asyncscript/{id}")
    public ResponseEntity<Object> asyncTrue(@PathVariable(name = "id") int id) {

        if (!scriptsToProceed.isEmpty()) {
            for (ClientScript cs : scriptsToProceed) {
                if (cs.getId() == id)
                    return ResponseEntity.accepted().body("script evaluation scheduled. script id for state/result request = " + id);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
