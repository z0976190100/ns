package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.IN_QUEUE;
import static com.z0976190100.restingnashorn.util.PseudoDB.*;

@Service
public class ProcessorManagerService {

    private ExecutorService processorsFixedPool = Executors.newFixedThreadPool(4);

    private ScriptManagerService scriptManagerService;

    @Autowired
    public ProcessorManagerService(ScriptManagerService scriptManagerService) {
        this.scriptManagerService = scriptManagerService;
    }

//TODO: remove launched scripts?

    public void launchScriptProcessing(int id) {

        Optional<ClientScript> targetClientScript = scriptManagerService.getScriptById(id);

        if (targetClientScript.isPresent()) {
            Processor processor = new Processor(targetClientScript.get(), "nashorn");
            processorsList.add(processor);
            processor.getProcessorState().setScriptStage(IN_QUEUE);
            processor.setTask(processorsFixedPool.submit(processor));

        }
    }


    @SuppressWarnings("deprecation")
    public ProcessorState killProcessor(int id) {

        Optional<ProcessorState> targetProcessorState =
                processorsList.stream()
                        .filter(el -> el.getId() == id)
                        .peek(el -> {
                            if (!el.getTask().isDone() && !el.getTask().isCancelled())
                                el.getTask().cancel(true);
                        })
                        .peek(el -> el.getThread().stop())
                        .map(Processor::getProcessorState)
                        .findFirst();

        return targetProcessorState.orElse(null);

    }
}
