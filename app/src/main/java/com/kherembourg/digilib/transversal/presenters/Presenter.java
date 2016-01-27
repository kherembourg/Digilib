package com.kherembourg.digilib.transversal.presenters;

public interface Presenter<T> {

    void attach(final T view);

    void detach();

}
