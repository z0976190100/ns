package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import static com.z0976190100.restingnashorn.util.AppVariables.processorList;

@Service
public class ProcessorStateService {

    public ProcessorState getProcessorState(int id){

        if (!processorList.isEmpty()) {
            for (Processor processor : processorList){
                return processor.getProcessorState();
            }
        }
        return null;
    }

}
