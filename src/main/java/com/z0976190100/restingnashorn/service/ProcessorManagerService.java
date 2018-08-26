package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.ClientScript;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.ABORTED;
import static com.z0976190100.restingnashorn.persistence.entity.ScriptStage.IN_QUEUE;
import static com.z0976190100.restingnashorn.util.PseudoDB.processorSet;

@Service
public class ProcessorManagerService {

    private int nThreads = 4;

    private ThreadPoolExecutor processorsFixedPool = new ThreadPoolExecutor(nThreads, nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());




    private ScriptManagerService scriptManagerService;

    @Autowired
    public ProcessorManagerService(ScriptManagerService scriptManagerService) {
        this.scriptManagerService = scriptManagerService;
    }

    public void launchScriptProcessing(int id) {

        Optional<ClientScript> targetClientScript = scriptManagerService.getScriptById(id);

        if (targetClientScript.isPresent()) {
            Processor processor = new Processor(targetClientScript.get(), "nashorn");
            processorSet.add(processor);
           // processorsList.add(processor);
            processor.getProcessorState().setScriptStage(IN_QUEUE);
            processor.setTask(processorsFixedPool.submit(processor));

        }
    }



    @SuppressWarnings("deprecation")
    public ProcessorState killProcessor(int id) {


        Optional<ProcessorState> targetProcessorState =
                processorSet.stream()
                        .filter(el -> el.getId() == id)
                        .peek(el -> {
                            if (!el.getTask().isDone() && !el.getTask().isCancelled())
                                el.getTask().cancel(true);
                            el.getThread().stop();
                        })
                        .map(el -> {
                            el.getProcessorState().setScriptStage(ABORTED);
                            return el.getProcessorState();
                        })
                        .findFirst();

        return targetProcessorState.orElse(null);

    }
}
