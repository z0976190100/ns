package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.util.AppVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller is responsible for launching a thread
 * for script processing:
 * # limitation of number of threads (default: 2)
 * # defining of engine type and options
 * # modifications of script body,
 * # evaluation.
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

    @PostMapping("/script/eval/{id}")
    public String processorLauncher(@PathVariable(name = "id") int id) {

        processorManagerService.launchProcessor(id);

        return "forward:/script/result/" + id;
    }

    @PostMapping("/script/kill/{id}")
    public ResponseEntity<ProcessorState> processorKill(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorManagerService.killProcessor(id);

        if (processorState != null)
            return ResponseEntity.status(200).body(processorState);
        return ResponseEntity.notFound().build();
    }
}
