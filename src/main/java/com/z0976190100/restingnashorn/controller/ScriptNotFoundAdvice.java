package com.z0976190100.restingnashorn.controller;


import com.z0976190100.restingnashorn.exceptions.ScriptNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ScriptNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ScriptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String scriptNotFoundHandler(ScriptNotFoundException e) {
        return e.getMessage();
    }
}
