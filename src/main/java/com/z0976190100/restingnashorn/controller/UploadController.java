package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
//TODO: scheduler for script management

@Controller
public class UploadController {

    private UploadService uploadService;
    private ProcessorManagerService processorManagerService;
    private URI location;

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
            location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(clientScript.getId())
                    .toUri();
            return "forward:/asyncscript/" + clientScript.getId();
        }

        return "forward:/script/eval/" + clientScript.getId();
    }

    @PostMapping("/asyncscript/{id}")
    public ResponseEntity<Object> asyncTrue(@PathVariable(name = "id") int id) {

        if (!scriptsToProceed.isEmpty()) {
            for (ClientScript cs : scriptsToProceed) {
                if (cs.getId() == id)

                    return ResponseEntity.created(location).build(); //body("script evaluation scheduled. script id for state||result request = " + id);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/script/{id}")
    public String checkState(@PathVariable(name = "id") int id) {

        return "forward:/script/state/" + id;

    }

}
