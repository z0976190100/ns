package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import static com.z0976190100.restingnashorn.util.AppVariables.processorsList;

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
                        processor.getThread().join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
/*
                   while (!processor.getProcessorState().isEvalDone()){

                       try {
                           wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
*/
                    return processor.getProcessorState();
                }
        }
        return null;
    }
}

