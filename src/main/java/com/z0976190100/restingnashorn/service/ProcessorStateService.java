package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

import static com.z0976190100.restingnashorn.util.AppVariables.processorsList;

@Service
public class ProcessorStateService {

    public ProcessorState getProcessorState(int id) {

        Optional<ProcessorState> targetProcessorState =
                processorsList.stream()
                        .filter(el -> el.getId() == id)
                        .map(Processor::getProcessorState)
                        .findFirst();

        return targetProcessorState.orElse(null);
//
//        if (!processorsList.isEmpty()) {
//            for (Processor processor : processorsList){
//                if(processor.getId() == id) return processor.getProcessorState();
//            }
//        }
    }

}
