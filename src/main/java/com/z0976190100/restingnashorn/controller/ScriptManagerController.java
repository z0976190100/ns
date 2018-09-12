package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.persistence.entity.Script;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.service.ProcessorStateService;
import com.z0976190100.restingnashorn.service.ScriptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

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
@RequestMapping("/scripts")
public class ScriptManagerController {

    private ScriptManagerService scriptManagerService;
    private ProcessorManagerService processorManagerService;
    private ProcessorStateService processorStateService;

    @Autowired
    ScriptManagerController(ScriptManagerService scriptManagerService,
                            ProcessorManagerService processorManagerService,
                            ProcessorStateService processorStateService) {
        this.scriptManagerService = scriptManagerService;
        this.processorManagerService = processorManagerService;
        this.processorStateService = processorStateService;
    }

    @PostMapping("/eval/{id}")
    public String provideScriptProcessing(@PathVariable(name = "id") int id) {

        processorManagerService.launchScriptProcessing(id);

        return "forward:/results/" + id;
    }

    @PostMapping("/kill/{id}")
    public ResponseEntity<ProcessorState> processorKill(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorManagerService.killProcessor(id);

        return processorState != null ? ResponseEntity.status(200).body(processorState) : ResponseEntity.notFound().build();

    }

    @PostMapping(value = "/")
    public String uploadClientScript(@RequestParam(name = "script") String ascript) {

        Script script = scriptManagerService.buildScript(ascript);
        scriptManagerService.registerScript(script);

        return "forward:/eval/" + script.getId();
    }


    @GetMapping("/")
    public ResponseEntity<List<Script>> getAllScripts() {
        return ResponseEntity.ok(scriptManagerService.getAllscripts());
    }

    @GetMapping({"/state/{id}","/{id}"})
    public ResponseEntity<ProcessorState> getProcessorState(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorStateService.getProcessorState(id);

        return processorState == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(processorState);
    }
}
