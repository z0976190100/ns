package com.z0976190100.restingnashorn.util;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PseudoDB {


    public static Set<Processor> processorSet = Collections.synchronizedSet(new HashSet<>());

    public static List<ClientScript> scriptsToProceed = Collections.synchronizedList(new ArrayList<>());

}
