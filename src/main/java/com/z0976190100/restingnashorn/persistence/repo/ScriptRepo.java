package com.z0976190100.restingnashorn.persistence.repo;

import com.z0976190100.restingnashorn.persistence.entity.Script;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.z0976190100.restingnashorn.util.PseudoDB.scriptsToProceed;

@Component
public class ScriptRepo {

    public Optional<Script> getScriptById(int id) {
        return scriptsToProceed.stream()
                .filter(el -> el.getId() == id)
                .findAny();
    }

    public void addScript(Script script) {
        scriptsToProceed.add(script);
        int id = scriptsToProceed.size();
        script.setId(id);
    }

    public List<Script> getAllscripts(){
        return scriptsToProceed;
    }
}
