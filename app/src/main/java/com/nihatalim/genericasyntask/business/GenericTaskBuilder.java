package com.nihatalim.genericasyntask.business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.nihatalim.genericasyntask.interfaces.OnBackgroundState;
import com.nihatalim.genericasyntask.interfaces.OnPostState;
import com.nihatalim.genericasyntask.interfaces.OnPreState;
import com.nihatalim.genericasyntask.models.User;

import java.lang.reflect.Type;
import java.util.concurrent.Callable;


/**
 * Created by thecower on 19.11.2017.
 */

public class GenericTaskBuilder {
    private int processTime = 5;

    private ProgressDialog progressDialog = null;
    private Context context = null;
    private Class ResponseType = null;
    private Object Parameter = null;
    private GenericTask Task = null;

    public GenericTaskBuilder() {

    }

    public static GenericTaskBuilder instance(){
        return new GenericTaskBuilder();
    }

    public GenericTaskBuilder ProgressDialog(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
        return this;
    }

    public GenericTaskBuilder ProcessTime(int processTime){
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

    public GenericTaskBuilder build(){
        this.Task = new GenericTask(this.context,this.ResponseType);
        return this;
    }

    public void execute(Object... requests){
        this.Task.execute(requests);
    }

    public GenericTask getTask() {
        return this.Task;
    }
}
