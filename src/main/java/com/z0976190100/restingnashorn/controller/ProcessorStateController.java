package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.service.ProcessorStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcessorStateController {

    private ProcessorStateService processorStateService;

    @Autowired
    ProcessorStateController(ProcessorStateService processorStateService) {
        this.processorStateService = processorStateService;
    }

    @GetMapping("/script/state/{id}")
    public ResponseEntity<ProcessorState> getProcessorState(int id) {

        ProcessorState processorState = processorStateService.getProcessorState(id);

       return processorState == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(processorState);
    }
}
