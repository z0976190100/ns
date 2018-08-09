package com.z0976190100.restingnashorn.controller;

import com.z0976190100.restingnashorn.persistence.entity.UserScript;
import com.z0976190100.restingnashorn.service.EvaluationService;
import com.z0976190100.restingnashorn.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ScriptController {

    private EvaluationService ne;
    private RegistrationService registrationService;

    @Autowired
    ScriptController(EvaluationService nengine,
                     RegistrationService registrationService) {
        this.ne = nengine;
        this.registrationService = registrationService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
            return ResponseEntity.status(200).body("test resp");
                //registrationService.register(new UserScript());
    }

    @PostMapping("/upload")
    public String uploadUserScript(@RequestParam(name = "script", defaultValue = "return 0;")
                                           String userScript) {

        return ne.consumeScript(userScript);
    }

    @GetMapping("/scriptstate")
    public String getScriptState() {
        return "state";
    }

    @PostMapping("/killscript")
    public String killScript() {
        return "killed";
    }
}
