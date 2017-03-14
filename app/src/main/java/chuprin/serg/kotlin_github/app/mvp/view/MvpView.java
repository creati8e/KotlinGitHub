package chuprin.serg.kotlin_github.app.mvp.view;

import android.content.Context;

import chuprin.serg.kotlin_github.app.mvp.MvpPresenter;

public interface MvpView<PRESENTER extends MvpPresenter> {

    /**
     * @return presenter associated with this view
     */
    PRESENTER getPresenter();

    /**
     * @return applicationContext
     */
    Context getContext();
}
