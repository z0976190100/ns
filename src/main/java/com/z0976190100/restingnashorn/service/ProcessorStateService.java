package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.z0976190100.restingnashorn.util.PseudoDB.processorsList;

@Service
public class ProcessorStateService {

    public ProcessorState getProcessorState(int id) {

        Optional<ProcessorState> targetProcessorState =
                processorsList.stream()
                        .filter(el -> el.getId() == id)
                        .map(Processor::getProcessorState)
                        .findFirst();

        return targetProcessorState.orElse(null);

    }

}
