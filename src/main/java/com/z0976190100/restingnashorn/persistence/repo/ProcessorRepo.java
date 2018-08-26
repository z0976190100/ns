package com.z0976190100.restingnashorn.persistence.repo;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.z0976190100.restingnashorn.util.PseudoDB.processorSet;

@Component
public class ProcessorRepo {

    public Processor getProcessorById(int id){

        Optional<Processor> targetProcessor = processorSet.stream()
                .filter(el -> el.getId() == id)
                .findAny();

        return targetProcessor.orElse(null);
    }

    public Processor remove(Processor processor){

        processorSet.remove(processor);

        return processor;
    }
}
