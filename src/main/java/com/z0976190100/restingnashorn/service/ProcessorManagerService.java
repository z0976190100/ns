package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Component;

import static com.z0976190100.restingnashorn.util.AppVariables.processorsList;
import static com.z0976190100.restingnashorn.util.AppVariables.scriptsToProceed;

@Component
public class ProcessorManagerService {


    public void launchProcessor(int id) {

        if(!scriptsToProceed.isEmpty()) {
            for (ClientScript cs : scriptsToProceed) {
                if (cs.getId() == id) {

                    Processor processor1 = new Processor(cs, "nashorn");
                    processorsList.add(processor1);
                    Thread thread1 = new Thread(processor1);
                    processor1.setThread(thread1);
                    thread1.start();
                }
            }
        }
    }

    public ProcessorState killProcessor(int id) {

        if (!processorsList.isEmpty()) {
            for (Processor processor : processorsList) {
                if (processor.getId() == id) {
                    //processor.getThread().notifyAll();
                    processor.getThread().interrupt();
                    return processor.getProcessorState();
                }

            }
        }
        return null;
    }
}
