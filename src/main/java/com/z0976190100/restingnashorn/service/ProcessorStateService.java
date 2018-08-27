package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.z0976190100.restingnashorn.util.PseudoDB.processorSet;

@Service
public class ProcessorStateService {

    private ProcessorManagerService processorManagerService;

    @Autowired
    ProcessorStateService(ProcessorManagerService processorManagerService){
        this.processorManagerService = processorManagerService;
    }

    public ProcessorState getProcessorState(int id) {

        Optional<ProcessorState> targetProcessorState =
                processorSet.stream()
                        .filter(el -> el.getId() == id)
                        .map(Processor::getProcessorState)
                        .findFirst();

        return targetProcessorState.orElse(null);

    }

}
