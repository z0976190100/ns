package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResultController {

    private ResultService resultService;

    @Autowired
    ResultController(ResultService resultService){
        this.resultService = resultService;
    }

    // for non-blocking scenario
    @GetMapping("/script/result/{id}")
    public ResponseEntity<ProcessorState> getResult(@PathVariable(value = "id") int id){

        ProcessorState processorState = resultService.getResult(id);

        if(processorState == null || !processorState.isEvalDone()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(processorState);
    }

    // for blocking scenario
    @PostMapping("/script/result/{id}")
    public ResponseEntity<ProcessorState> postOnResultPending(@PathVariable(value = "id") int id){

        return ResponseEntity.ok().body(resultService.postOnResultPending(id));
    }
}
