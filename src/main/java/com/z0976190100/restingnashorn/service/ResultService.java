package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import static com.z0976190100.restingnashorn.util.AppVariables.processorsList;
import static java.lang.Thread.sleep;

// TODO: is getResult() good enough?

@Service
public class ResultService {

    public ProcessorState getResult(int id) {
        return postOnResultPending(id);
    }

    public ProcessorState postOnResultPending(int id) {

        if (!processorsList.isEmpty()) {
            for (Processor processor : processorsList)
                if (processor.getId() == id) {

                    try {
                        while (processor.getTask().get() != null) {
                            sleep(100);
                        }
//                        processor.getThread().join();
                    } catch (ExecutionException | InterruptedException | CancellationException e) {
                        e.printStackTrace();
                    }
                    return processor.getProcessorState();
                }
        }
        return null;
    }
}

