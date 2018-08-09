package com.z0976190100.restingnashorn.util;

import com.z0976190100.restingnashorn.persistence.entity.UserScript;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;

@Component
public class EnvironmentVariables {
    
    public int scriptId;

    private static PriorityQueue<UserScript> validationQueue;
    private static PriorityQueue<UserScript> evaluationQueue;

    public static PriorityQueue<UserScript> getValidationQueue() {
        return validationQueue;
    }

    public static PriorityQueue<UserScript> getEvaluationQueue() {
        return evaluationQueue;
    }
}
