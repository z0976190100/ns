package com.z0976190100.restingnashorn.service;


import com.z0976190100.restingnashorn.persistence.entity.UserScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineDispatcherService {



    private RegistrationService registrationService;
    private UserScript userScript;

   @Autowired
    EngineDispatcherService(RegistrationService rs,
                            UserScript us){
       this.registrationService = rs;
       this.userScript = us;
   }

   private void registrateScript(UserScript us){
       registrationService.register(us);
   }

   private String validateScript(UserScript us){
       String result = "ok";

       return result;
   }
}
