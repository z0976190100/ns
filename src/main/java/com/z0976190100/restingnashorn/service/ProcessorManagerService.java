package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Component;

import static com.z0976190100.restingnashorn.util.AppVariables.processorList;
import static com.z0976190100.restingnashorn.util.AppVariables.scriptsToProceed;

@Component
public class ProcessorManagerService {


    public void launchProcessor(int id) {
        ClientScript clientScript = scriptsToProceed.get(id-1);
        Processor processor1 = new Processor(clientScript, "nashorn");
        processorList.add(processor1);
        Thread thread1 = new Thread(processor1);
        processor1.setThread(thread1);
        thread1.start();
    }

    public ProcessorState killProcessor(int id) {

        if (!processorList.isEmpty()) {
            for (Processor processor : processorList) {
                if (processor.getId() == id) {
                    processor.getThread().destroy();
                    return processor.getProcessorState();
                }

            }
        }
        return null;
    }
}
