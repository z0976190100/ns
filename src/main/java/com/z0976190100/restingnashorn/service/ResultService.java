package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import static com.z0976190100.restingnashorn.util.AppVariables.processorList;

@Service
public class ResultService {


   synchronized public ProcessorState getResult(int id) {

        if (!processorList.isEmpty()) {
            for (Processor processor : processorList)
                if (processor.getId() == id) {
                   while (!processor.getProcessorState().isEvalDone()){

                       try {
                           wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                           return processor.getProcessorState();
                       }
                   }
                }
        }
        return null;
    }
}

