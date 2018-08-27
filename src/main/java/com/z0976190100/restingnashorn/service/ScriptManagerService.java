package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.repo.ScriptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScriptManagerService {

    private ScriptRepo scriptRepo;

    @Autowired
    ScriptManagerService(ScriptRepo scriptRepo){
        this.scriptRepo = scriptRepo;
    }

    public ClientScript buildScript(String ascript) {
        return new ClientScript(ascript);
    }

    public void registerScript(ClientScript clientScript) {

        scriptRepo.addScript(clientScript);

    }

    public Optional<ClientScript> getScriptById(int id){
        return scriptRepo.getScriptById(id);
    }

    public List<ClientScript> getAllscripts(){
        return scriptRepo.getAllscripts();
    }
}
