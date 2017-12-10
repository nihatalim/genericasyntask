package com.nihatalim.generictaskbuilder.business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.CountDownTimer;
import com.nihatalim.generictaskbuilder.interfaces.*;


/**
 * Created by thecower on 19.11.2017.
 */

public class GenericTaskBuilder {
    private long processTime = 10000;
    private long intervalTime = 1000;

    private ProgressDialog progressDialog = null;
    private Context context = null;
    private Class ResponseType = null;
    private Object Parameter = null;
    private GenericTask Task = null;

    private boolean executed = false;

    private TimeoutProcess timeoutProcess = null;

    public GenericTaskBuilder() {

    }

    public static GenericTaskBuilder instance(){
        return new GenericTaskBuilder();
    }

    public GenericTaskBuilder ProgressDialog(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
        return this;
    }

    public GenericTaskBuilder ProcessTime(long processTime){
        this.processTime = processTime;
        return this;
    }

    public GenericTaskBuilder Context(Context context){
        this.context = context;
        return this;
    }

    public GenericTaskBuilder ResponseType(Class ResponseType){
        this.ResponseType = ResponseType;
        return this;
    }

    public GenericTaskBuilder OnPreState(OnPreState state){
        this.Task.setOnPreExecute(state);
        return this;
    }

    public GenericTaskBuilder OnPostState(OnPostState state){
        this.Task.setOnPostExecute(state);
        return this;
    }

    public GenericTaskBuilder OnBackgroundState(OnBackgroundState state){
        this.Task.setOnBackground(state);
        return this;
    }

    public GenericTaskBuilder OnProgressUpdateState(OnProgressUpdateState state){
        this.Task.setOnProgressUpdateState(state);
        return this;
    }

    public GenericTaskBuilder OnTimedOut(TimeoutProcess state){
        this.timeoutProcess = state;
        return this;
    }

    public GenericTaskBuilder build(){
        this.Task = new GenericTask(this.context,this.ResponseType);
        return this;
    }

    public void execute(Object... requests){
        this.executed = true;
        this.Task.setCountDownTimer(new CountDownTimer(processTime,intervalTime){
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Task.cancel(true);
                Task.getProgressDialog().dismiss();
                if(timeoutProcess !=null){
                    timeoutProcess.run();
                }
                //Toast.makeText(context, context.getString(R.string.timeout_error), Toast.LENGTH_SHORT).show();
            }
        });

        this.Task.getCountDownTimer().start();
        this.Task.execute(requests);
    }

    public GenericTask getTask() {
        return this.Task;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

}
