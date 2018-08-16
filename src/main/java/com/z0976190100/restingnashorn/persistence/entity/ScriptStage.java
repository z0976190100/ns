package com.z0976190100.restingnashorn.persistence.entity;


/**
 * Class for script lifecycle stage representation
 */
public enum ScriptStage {

    //TODO: getter for processorState

    IN_QUEUE, PROCESSING_EVALUATION, ERROR_OF_EVALUATION, AFTER_EVALUATION, COMPILED;
}
