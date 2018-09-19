package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.persistence.entity.Script;
import com.z0976190100.restingnashorn.service.ProcessorService;
import com.z0976190100.restingnashorn.service.ProcessorStateService;
import com.z0976190100.restingnashorn.service.ScriptManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

// TODO: plug Spring Environment for REST messages

@RestController
@RequestMapping("/scripts")
public class ScriptController {

    private ScriptManagerService scriptManagerService;
    private ProcessorService processorService;
    private ProcessorStateService processorStateService;
    private Environment environment;

    @Autowired
    ScriptController(ScriptManagerService scriptManagerService,
                     ProcessorService processorService,
                     ProcessorStateService processorStateService,
                     Environment environment) {
        this.scriptManagerService = scriptManagerService;
        this.processorService = processorService;
        this.processorStateService = processorStateService;
        this.environment = environment;
    }

    @PostMapping("/eval/{id}")
    String provideScriptProcessing(@PathVariable(name = "id") int id) {

        processorService.launchScriptProcessing(id);

        return "forward:/results/" + id;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProcessorState> processorKill(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorService.killProcessor(id);

        return processorState != null ? ResponseEntity.status(200).body(processorState) : ResponseEntity.notFound().build();

    }

    @PostMapping(value = "/")
    String uploadClientScript(@RequestParam(name = "script") String ascript) {

        Script script = scriptManagerService.buildScript(ascript);
        scriptManagerService.registerScript(script);

        return "forward:/eval/" + script.getId();
    }


    @GetMapping("/")
    ResponseEntity<List<Script>> getAllScripts() throws Exception {
        return ResponseEntity.ok(scriptManagerService.getAllscripts());
    }

    @GetMapping("/{id}")
    Resource<Script> getScript(@PathVariable int id) throws Exception {  // TODO: implement custom exceptions and exceptionHandlers

        // Exception: no entry was found -- Status code: 404 -- Message: no entry with such {id} exist
        // invalid URI (wrong data type ex.) -- 422 -- hint with possible variants of URI content

        Script script = scriptManagerService.getScriptById(id)
                .orElseThrow(() -> new Exception("scriptNotFound"));

        return new Resource<Script>(script,
                linkTo(methodOn(ScriptController.class).getScript(id)).withSelfRel(),
                linkTo(methodOn(ScriptController.class).getAllScripts()).withRel("scripts"));
    }



    @GetMapping({"/state/{id}","/{id}"})
    ResponseEntity<ProcessorState> getProcessorState(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorStateService.getProcessorState(id);

        return processorState == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(processorState);
    }
}
