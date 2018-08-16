package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.persistence.entity.ScriptStage;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.IN_QUEUE;
import static com.z0976190100.restingnashorn.util.AppVariables.processorsList;
import static com.z0976190100.restingnashorn.util.AppVariables.scriptsToProceed;

@Service
public class ProcessorManagerService {

    static {
        bootstrapEnvironment(4);
    }

    private static ExecutorService processorsFixedPool;

    private static void bootstrapEnvironment(int processorsNumber){
         processorsFixedPool = Executors.newFixedThreadPool(processorsNumber);

    }


    public void launchProcessor(int id) {

        if(!scriptsToProceed.isEmpty()) {

            for (ClientScript cs : scriptsToProceed) {
                if (cs.getId() == id) {
                    Processor processor1 = new Processor(cs, "nashorn");
                    processorsList.add(processor1);
                    processor1.getClientScript().setStage(IN_QUEUE);
                    processorsFixedPool.submit(processor1);
//                    Thread thread1 = new Thread(processor1);
//                    processor1.setThread(thread1);
//                    thread1.start();
                }
            }
        }
    }

    public ProcessorState killProcessor(int id) {

        if (!processorsList.isEmpty()) {
            for (Processor processor : processorsList) {
                if (processor.getId() == id) {
                    //processor.getThread().notifyAll();
                    processor.getThread().stop();
                    return processor.getProcessorState();
                }
            }
        }
        return null;
    }
}
