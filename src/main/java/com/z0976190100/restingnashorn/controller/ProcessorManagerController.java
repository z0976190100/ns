package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller is responsible for launching a task
 * of script processing.
 * <p>
 * Also it should be used by client for managing script-
 * processing lifecycle:
 * # termination of evaluation
 * #
 */

@Controller
public class ProcessorManagerController {

    private ProcessorManagerService processorManagerService;

    @Autowired
    ProcessorManagerController(ProcessorManagerService processorManagerService) {
        this.processorManagerService = processorManagerService;
    }

    @PostMapping("/scripts/eval/{id}")
    public String provideScriptProcessing(@PathVariable(name = "id") int id) {

        processorManagerService.launchScriptProcessing(id);

        return "forward:/results/" + id;
    }

    @PostMapping("/scripts/kill/{id}")
    public ResponseEntity<ProcessorState> processorKill(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorManagerService.killProcessor(id);

        return processorState != null ? ResponseEntity.status(200).body(processorState) : ResponseEntity.notFound().build();

    }
}
