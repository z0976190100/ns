package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.service.ProcessorStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * As result of creating and launching a task in
 * <code>ProcessorManagerService</code> appears an instance
 * of <code>Processor</code>
 * witch owns an instance of <code>ProcessorState</code>
 * convenient class for monitoring state of evaluation process
 * of script.
 * This controller serves for purposes of state retrieving.
 */


@RestController
public class ProcessorStateController {

    private ProcessorStateService processorStateService;

    @Autowired
    ProcessorStateController(ProcessorStateService processorStateService) {
        this.processorStateService = processorStateService;
    }

    @GetMapping({"/script/state/{id}","/script/{id}"})
    public ResponseEntity<ProcessorState> getProcessorState(@PathVariable(name = "id") int id) {

        ProcessorState processorState = processorStateService.getProcessorState(id);

       return processorState == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(processorState);
    }
}
