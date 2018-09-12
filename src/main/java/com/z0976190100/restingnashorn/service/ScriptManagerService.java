package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Script;
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

    public Script buildScript(String ascript) {
        return new Script(ascript);
    }

    public void registerScript(Script script) {

        scriptRepo.addScript(script);

    }

    public Optional<Script> getScriptById(int id){
        return scriptRepo.getScriptById(id);
    }

    public List<Script> getAllscripts(){
        return scriptRepo.getAllscripts();
    }
}
