package com.nihatalim.generictaskbuilder.business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.nihatalim.generictaskbuilder.interfaces.OnBackgroundState;
import com.nihatalim.generictaskbuilder.interfaces.OnPostState;
import com.nihatalim.generictaskbuilder.interfaces.OnPreState;
import com.nihatalim.generictaskbuilder.interfaces.OnProgressUpdateState;

/**
 * Created by thecower on 19.11.2017.
 */

public class GenericTask<TRequest, TProgress, TResponse> extends AsyncTask<TRequest, TProgress, TResponse> {
    private ProgressDialog progressDialog = null;
    private Class<TResponse> ResponseClass = null;
    private Context context = null;

    private OnPreState onPreExecute = null;
    private OnBackgroundState<TRequest,TResponse> onBackground = null;
    private OnPostState<TResponse> onPostExecute = null;
    private OnProgressUpdateState onProgressUpdateState = null;

    private CountDownTimer countDownTimer = null;


    public GenericTask(Context context, Class<TResponse> ResponseClass) {
        this.context = context;
        this.ResponseClass = ResponseClass;

        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage("Processing...");
    }

    public GenericTask(Context context, Class<TResponse> ResponseClass, OnPreState onPreExecute, OnBackgroundState onBackground, OnPostState onPostExecute) {
        this(context, ResponseClass);
        this.onPreExecute = onPreExecute;
        this.onBackground = onBackground;
        this.onPostExecute = onPostExecute;

        // Basics
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog.show();
        if(this.onPreExecute != null){
            this.onPreExecute.run();
        }
    }

    @Override
    protected void onPostExecute(TResponse tResponse) {
        super.onPostExecute(tResponse);
        this.getCountDownTimer().cancel();
        if(this.onPostExecute != null){
            this.onPostExecute.run(tResponse);
        }
        this.progressDialog.dismiss();
    }

    @Override
    protected TResponse doInBackground(TRequest... tRequests) {
        if (this.onBackground != null) {
            return this.onBackground.run(tRequests);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(TProgress... values) {
        super.onProgressUpdate(values);
        this.onProgressUpdateState.run(values);
    }

    public OnPreState getOnPreExecute() {
        return onPreExecute;
    }

    public void setOnPreExecute(OnPreState onPreExecute) {
        this.onPreExecute = onPreExecute;
    }

    public OnBackgroundState<TRequest, TResponse> getOnBackground() {
        return onBackground;
    }

    public void setOnBackground(OnBackgroundState<TRequest, TResponse> onBackground) {
        this.onBackground = onBackground;
    }

    public OnPostState<TResponse> getOnPostExecute() {
        return onPostExecute;
    }

    public void setOnPostExecute(OnPostState<TResponse> onPostExecute) {
        this.onPostExecute = onPostExecute;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public OnProgressUpdateState getOnProgressUpdateState() {
        return onProgressUpdateState;
    }

    public void setOnProgressUpdateState(OnProgressUpdateState onProgressUpdateState) {
        this.onProgressUpdateState = onProgressUpdateState;
    }

    public void publishProgressTask(TProgress... objects){
        this.publishProgress(objects);
    }
}
