package com.z0976190100.restingnashorn.service;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Objects;


public class WriterHandler implements Runnable{

    private Writer consoleOut;
    private int writerHashcode;
    private int writerChangeCounter;
    private boolean stopWatching;
    public static String test = "test";

    public WriterHandler(){
        this.consoleOut = new StringWriter();
        this.writerChangeCounter = 0;
    }

    public void logg(String output){
        System.out.println("console log " + output);
    }

    @Override
    public void run() {
        System.out.println("wathcher is ON watch!");
        writerHashcode = this.hashCode();
        changeWatcher();
        System.out.println("watcher is down");
    }

    private void changeWatcher(){

        while (!stopWatching){
            System.out.println(writerHashcode);
            System.out.println("writer state on watch:  "+ consoleOut);
            System.out.println("im watching!!!");
            int newhc = hashCode();
            System.out.println(newhc);
            if(writerHashcode != newhc){
               onChange(newhc);
            }
        }

    }

    private void onChange(int hc) {
        writerHashcode = hc;
        ++ writerChangeCounter;
    }

    // TODO: a nice and beautiful hashCode thy shalt implement!


    public void setStopWatching(boolean stopWatching) {
        this.stopWatching = stopWatching;
    }

    public Writer getConsoleOut() {
        return consoleOut;
    }

    public int getWriterChangeCounter() {
        return writerChangeCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WriterHandler)) return false;
        WriterHandler that = (WriterHandler) o;
        return writerHashcode == that.writerHashcode &&
                getWriterChangeCounter() == that.getWriterChangeCounter() &&
                stopWatching == that.stopWatching &&
                Objects.equals(getConsoleOut(), that.getConsoleOut());
    }

    @Override
    public int hashCode() {
String writerValue = consoleOut.toString();
        return writerValue.hashCode();
    }
}
