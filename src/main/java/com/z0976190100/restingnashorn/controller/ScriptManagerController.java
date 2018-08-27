package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.service.ScriptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * <code>ScriptManagerController</code> is responsible for proceeding http requests
 * for uploading script from request body to proceed it with
 * Nashorn engine then.
 * Client-provided script is a parameter of POST request body.
 * For clear, convenient and controllable management of script lifecycle in app,
 * registration and queuing is provided.
 * For managing of script it got to have <code>id</code>,
 * which will be defined in <code>ScriptManagerService</code>
 **/


@Controller
public class ScriptManagerController {

    private ScriptManagerService scriptManagerService;

    @Autowired
    ScriptManagerController(ScriptManagerService scriptManagerService) {
        this.scriptManagerService = scriptManagerService;
    }

    @PostMapping(value = "/script")
    public String uploadClientScript(@RequestParam(name = "script") String ascript) {

        ClientScript clientScript = scriptManagerService.buildScript(ascript);
        scriptManagerService.registerScript(clientScript);

        return "forward:/script/eval/" + clientScript.getId();
    }


    @GetMapping("/script/all")
    public ResponseEntity<List<ClientScript>> getAllScripts() {
        return ResponseEntity.ok(scriptManagerService.getAllscripts());
    }

}
