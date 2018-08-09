package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.UserScript;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegistrationService {

    int id = 0;

    // setting id, if not defined in request,
    // setting priority, if not defined in request,
    // adding to queue on evaluation

    public String  register(UserScript script){

        return String.valueOf(id++);
//        script.setId(1);
//        script.setPriority(1);

    }
}
