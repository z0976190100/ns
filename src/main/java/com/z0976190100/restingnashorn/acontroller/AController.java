package com.z0976190100.restingnashorn.acontroller;


import com.z0976190100.restingnashorn.anentity.Aresult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ascript")
public class AController {

    @PostMapping("/")
    public ResponseEntity<Aresult> setScript(@RequestParam(name = "script") String ascript){

        // Aresult aResult = aResultService.getResult(ascript);
        return ResponseEntity.ok(new Aresult());
    }

}
