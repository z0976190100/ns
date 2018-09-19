package com.z0976190100.restingnashorn.exceptions;

public class ScriptNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "script not found ";
    }
}
