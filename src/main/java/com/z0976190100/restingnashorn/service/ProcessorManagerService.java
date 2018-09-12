package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Script;
import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import com.z0976190100.restingnashorn.persistence.repo.ProcessorRepo;
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
    private ProcessorRepo processorRepo;

    @Autowired
    public ProcessorManagerService(ScriptManagerService scriptManagerService,
                                   ProcessorRepo processorRepo) {
        this.scriptManagerService = scriptManagerService;
        this.processorRepo = processorRepo;
    }

    public void launchScriptProcessing(int id) {

        Optional<Script> targetClientScript = scriptManagerService.getScriptById(id);

        if (targetClientScript.isPresent()) {
            Processor processor = new Processor(targetClientScript.get(), "nashorn");
            processorSet.add(processor);
            processor.getProcessorState().setScriptStage(IN_QUEUE);
            processor.setTask(processorsFixedPool.submit(processor));

        }
    }


    @SuppressWarnings("deprecation")
    public ProcessorState killProcessor(int id) {

        Processor targetProcessor = processorRepo.getProcessorById(id);

        if (targetProcessor != null && !targetProcessor.getTask().isDone() && !targetProcessor.getTask().isCancelled()) {

            targetProcessor.getTask().cancel(true);
            targetProcessor.getThread().stop();
            targetProcessor.getProcessorState().setScriptStage(ABORTED);

            return processorRepo.remove(targetProcessor).getProcessorState();

        }

        return null;


//        Optional<Processor> targetProcessorState =
//                processorSet.stream()
//                        .filter(el -> el.getId() == id)
//                        .map(el -> {
//                            if (!el.getTask().isDone() && !el.getTask().isCancelled())
//                                el.getTask().cancel(true);
//                            el.getThread().stop();
//                            el.getProcessorState().setScriptStage(ABORTED);
//                            return el;
//                        })
//                        .findFirst();
//
//        return targetProcessorState.orElse(null);

    }
}
