package com.z0976190100.restingnashorn.util;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;

import java.util.ArrayList;
import java.util.List;


public class AppVariables {

    //TODO: scriptsToProceed to HashSet
    //TODO: thread-safe collections?

    // restriction of script processing threads
    //public static int processorsNumber = 2;
    // application-scoped list of all processors
    volatile public static List<Processor> processorsList = new ArrayList<>();

    // application-scoped LILO queue of received scripts to proceed
    volatile public static List<ClientScript> scriptsToProceed = new ArrayList<>();

}
