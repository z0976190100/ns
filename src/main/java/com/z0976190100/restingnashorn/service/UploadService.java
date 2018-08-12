package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.util.AppVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.z0976190100.restingnashorn.util.AppVariables.scriptsToProceed;

@Service
public class UploadService {

    public ClientScript buildScript(String ascript) {
        return new ClientScript();
    }

    // LILO variant of
    public ClientScript registerScript(ClientScript clientScript) {

       scriptsToProceed.add(clientScript);
        int id = scriptsToProceed.size();
        clientScript.setId(id);

        return clientScript;
    }
}
