package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.service.NashEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ScriptController {

    private NashEngine ne;

    @Autowired
    ScriptController(NashEngine nengine) {
        this.ne = nengine;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/scriptstate")
    public String state(){
        return "state";
    }

@PostMapping("/script")
    public String evaluateUserScript(@RequestParam(name = "script", defaultValue = "return 0;")
                                                   String userScript) {

        return  ne.consumeScript(userScript);
    }
}
