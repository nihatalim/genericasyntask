package com.nihatalim.genericasyntask.business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by thecower on 19.11.2017.
 */

public class GenericTask<TRequest, TResponse> extends AsyncTask<TRequest, Void, TResponse> {
    private ProgressDialog progressDialog = null;
    private Class<TResponse> ResponseClass = null;
    private Context context = null;

    public GenericTask(Context context, Class<TResponse> ResponseClass) {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(TResponse tResponse) {
        super.onPostExecute(tResponse);
    }

    @Override
    protected TResponse doInBackground(TRequest... tRequests) {
        return null;
    }

}
