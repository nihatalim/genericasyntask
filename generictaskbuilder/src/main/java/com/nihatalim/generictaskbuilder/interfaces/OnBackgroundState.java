package com.nihatalim.generictaskbuilder.interfaces;

/**
 * Created by thecower on 20.11.2017.
 */

public interface OnBackgroundState<TRequest,TResponse> {
    TResponse run(TRequest... requests);
}
