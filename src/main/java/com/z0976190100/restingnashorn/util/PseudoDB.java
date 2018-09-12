package com.z0976190100.restingnashorn.util;

import com.z0976190100.restingnashorn.persistence.entity.Script;
import com.z0976190100.restingnashorn.persistence.entity.Processor;

import java.util.*;


public class PseudoDB {


    public static Set<Processor> processorSet = Collections.synchronizedSet(new HashSet<>());

    public static List<Script> scriptsToProceed = Collections.synchronizedList(new ArrayList<>());

}
