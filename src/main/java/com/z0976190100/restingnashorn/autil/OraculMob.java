package com.z0976190100.restingnashorn.autil;

import com.z0976190100.restingnashorn.anentity.Aresult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * TODO: async variant
 */

public class OraculMob {

   private Map<String, FutureTask<Aresult>> aResultJar = new ConcurrentHashMap<>();

    private ExecutorService mob = Executors.newFixedThreadPool(4);

    public void pushToMob(Oracul aresultOracul) {

        FutureTask<Aresult> yesOrNo = new FutureTask<>(aresultOracul);

        aResultJar.put("particularScriptStringOrID", yesOrNo); // and what if key already exists???

        mob.submit(yesOrNo);


    }

    // request for state of execution of the FutureTask object
    public synchronized String askAJar(String scriptOrID){
        return aResultJar.get(scriptOrID).toString();
    }

}
