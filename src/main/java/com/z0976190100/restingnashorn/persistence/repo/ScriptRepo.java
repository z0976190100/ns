package com.z0976190100.restingnashorn.persistence.repo;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.z0976190100.restingnashorn.util.PseudoDB.scriptsToProceed;

@Component
public class ScriptRepo {

    public Optional<ClientScript> getScriptById(int id) {
        return scriptsToProceed.stream()
                .filter(el -> el.getId() == id)
                .findAny();
    }

    public void addScript(ClientScript clientScript) {
        scriptsToProceed.add(clientScript);
        int id = scriptsToProceed.size();
        clientScript.setId(id);
    }

    public List<ClientScript> getAllscripts(){
        return scriptsToProceed;
    }
}
