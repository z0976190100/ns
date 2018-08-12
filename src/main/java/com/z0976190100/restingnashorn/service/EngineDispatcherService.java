package com.z0976190100.restingnashorn.service;


import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.util.AppVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineDispatcherService {

    private RegistrationService registrationService;
    private ModificationService modificationService;
    private AppVariables appVariables;


   @Autowired
    EngineDispatcherService(RegistrationService rs,
                            ModificationService ms,
                            AppVariables av){
       this.registrationService = rs;
       this.modificationService = ms;
       this.appVariables = av;
   }


   private void registerScript(ClientScript us){
       registrationService.register(us);
   }

   public ProcessorState runEval(ClientScript clientScript){

       registerScript(clientScript);
       modificationService.modifyScript(clientScript, "print");

       Processor nashorn = new Processor(clientScript, "nashorn", clientScript.getId());

       appVariables.processorList.add(nashorn);

       Thread processor1 = new Thread(nashorn);

       processor1.start();

       try {
           processor1.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       return nashorn.getProcessorState();
   }

   private String validateScript(ClientScript us){
       String result = "ok";

       return result;
   }
}
