package com.nihatalim.genericasyntask.business;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.concurrent.Callable;


/**
 * Created by thecower on 19.11.2017.
 */

public class GenericTaskBuilder {
    private ProgressDialog progressDialog = null;
    private int processTime = 5;

    public GenericTaskBuilder() {

    }

    public GenericTaskBuilder ProgressDialog(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
        return this;
    }

    public GenericTaskBuilder ProcessTime(int processTime){
        this.processTime = processTime;
        return this;
    }

}
