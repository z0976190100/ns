package com.z0976190100.restingnashorn.util;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AppVariables {

    // application-scoped list of all processors
    public static List<Processor> processorsList = Collections.synchronizedList(new ArrayList<>());

    // application-scoped LILO queue of received scripts to proceed
    public static List<ClientScript> scriptsToProceed = Collections.synchronizedList(new ArrayList<>());

}
