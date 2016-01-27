package com.kherembourg.digilib.transversal.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.kherembourg.digilib.transversal.presenters.Presenter;
import com.kherembourg.digilib.transversal.presenters.PresenterView;

public abstract class BaseFragment extends Fragment implements PresenterView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent();
        if(getPresenter() != null) {
            getPresenter().attach(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(getPresenter() != null) {
            getPresenter().detach();
        }
    }

    protected abstract void injectComponent();

    protected abstract Presenter getPresenter();
}
