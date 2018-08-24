package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.service.ScriptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

// TODO: get all scripts ids mapping

@Controller
public class ScriptManagerController {

    private ScriptManagerService scriptManagerService;
    private ProcessorManagerService processorManagerService;
    private URI location;

    @Autowired
    ScriptManagerController(ScriptManagerService scriptManagerService,
                            ProcessorManagerService processorManagerService) {
        this.scriptManagerService = scriptManagerService;
        this.processorManagerService = processorManagerService;
    }

    @PostMapping("/script")
    public String uploadClientScript(@RequestParam(name = "script") String ascript,
                                     @RequestParam(name = "async", defaultValue = "false") boolean async) {

        ClientScript clientScript = scriptManagerService.buildScript(ascript);
        scriptManagerService.registerScript(clientScript);

        if (async) {
            processorManagerService.launchScriptProcessing(clientScript.getId());
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
    public ResponseEntity<Object> asyncTrue(@PathVariable(name = "id") int id,
                                            @PathVariable(name = "async") boolean async) {

        if (async) {
            Optional<ClientScript> targetClientScript = scriptManagerService.getScriptById(id);

            if (targetClientScript.isPresent())
                return ResponseEntity.created(location).build(); //body("script evaluation scheduled. script id for state||result request = " + id);

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/script/{id}")
    public ResponseEntity<ClientScript> getScript(@RequestParam(name = "id") int id) {

        Optional<ClientScript> targetClientScript = scriptManagerService.getScriptById(id);

        if (targetClientScript.isPresent())
            return ResponseEntity.created(location).build();

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/script/all")
    public ResponseEntity<List<ClientScript>> getAllScripts() {
        return ResponseEntity.ok(scriptManagerService.getAllscripts());
    }

}
