package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.IN_QUEUE;
import static com.z0976190100.restingnashorn.util.AppVariables.*;

@Service
public class ProcessorManagerService {



//TODO: remove launched scripts?
    public void launchProcessor(int id) {

        if (!scriptsToProceed.isEmpty()) {
// TODO: clone() for ClientScript, to pass clone to processors?
            for (ClientScript cs : scriptsToProceed) {
                if (cs.getId() == id) {
                    Processor processor = new Processor(cs, "nashorn");
                    processorsList.add(processor);
                    processor.getProcessorState().setScriptStage(IN_QUEUE);
                    processor.setTask(processorsFixedPool.submit(processor));
//                    Thread thread1 = new Thread(processor1);
//                    processor1.setThread(thread1);
//                    thread1.start();
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public ProcessorState killProcessor(int id) {

        Optional<ProcessorState> targetProcessorState =
                processorsList.stream()
                        .filter(el -> el.getId() == id)
                        .peek(el -> {
                            el.getTask().cancel(true);
                        })
                        .peek(el -> el.getThread().stop())
                        .map(Processor::getProcessorState)
                        .findFirst();

        return targetProcessorState.orElse(null);

      /*  if (!processorsList.isEmpty()) {
            for (Processor processor : processorsList) {
                if (processor.getId() == id) {
                    //processor.getThread().notifyAll();
                    processor.getTask().cancel(true);
                    return processor.getProcessorState();
                }
            }
        }
        return null;
    }*/
    }
}
