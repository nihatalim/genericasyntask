package com.nihatalim.generictaskbuilder.interfaces;

/**
 * Created by thecower on 26.11.2017.
 */

public interface OnProgressUpdateState<T> {
    void run(T... obj);
}
