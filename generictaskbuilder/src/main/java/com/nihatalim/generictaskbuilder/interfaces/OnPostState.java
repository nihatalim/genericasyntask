package com.nihatalim.generictaskbuilder.interfaces;

/**
 * Created by thecower on 20.11.2017.
 */

public interface OnPostState<TResponse> {
    void run(TResponse response);
}
