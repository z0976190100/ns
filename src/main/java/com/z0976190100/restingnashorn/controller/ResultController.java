package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller works in async mode, so even on pending of
 * result response it is available for incoming requests.
 *
 * Also, this controller provides async and sync scenarios
 * depending on value of request parameter <code>async</code>.
 *
 * Note: after retrieving result of evaluation
 * particular <code>Processor</code> is being removed
 * from <code>processorsSet</code>.
 */

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResultController {

    private URI location;
    private ResultService resultService;

    @Autowired
    ResultController(ResultService resultService){
        this.resultService = resultService;
    }


    @GetMapping("/script/result/{id}")
    public ResponseEntity<ProcessorState> getResult(@PathVariable(value = "id") int id){

        ProcessorState processorState = resultService.getResult(id);

        if(processorState == null || !processorState.isEvalDone()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(processorState);
    }


    @PostMapping("/script/result/{id}")
    public ResponseEntity<ProcessorState> postOnResultPending(@PathVariable(value = "id") int id,
                                                              @RequestParam(name = "async", defaultValue = "false") boolean async){

        if (async) {
            location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("")
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        }

        return ResponseEntity.ok().body(resultService.postOnResultPending(id));
    }
}
