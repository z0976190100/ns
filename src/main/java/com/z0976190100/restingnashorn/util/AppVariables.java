package com.z0976190100.restingnashorn.util;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AppVariables {



    public static ExecutorService processorsFixedPool = Executors.newFixedThreadPool(4);


    // application-scoped list of all processors
    public static List<Processor> processorsList = Collections.synchronizedList(new ArrayList<>());

    // application-scoped LILO queue of received scripts to proceed
    public static List<ClientScript> scriptsToProceed = Collections.synchronizedList(new ArrayList<>());

}
