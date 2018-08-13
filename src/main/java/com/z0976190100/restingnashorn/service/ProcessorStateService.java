package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import static com.z0976190100.restingnashorn.util.AppVariables.processorsList;

@Service
public class ProcessorStateService {

    public ProcessorState getProcessorState(int id){

        if (!processorsList.isEmpty()) {
            for (Processor processor : processorsList){
                if(processor.getId() == id) return processor.getProcessorState();
            }
        }
        return null;
    }

}
