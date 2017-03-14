package chuprin.serg.kotlin_github.app.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import chuprin.serg.kotlin_github.app.mvp.MvpPresenter;

public abstract class MvpActivity<PRESENTER extends MvpPresenter> extends AppCompatActivity
        implements MvpView<PRESENTER> {

    private MvpViewHelper<MvpActivity<PRESENTER>> helper;
    private boolean mRecreating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        helper = new MvpViewHelper<>(this);
        helper.onCreate();
        initPresenter();
        getPresenter().attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
        helper.onDestroy(mRecreating || isChangingConfigurations());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mRecreating = true;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    protected abstract
    @LayoutRes
    int getLayoutRes();

    /**
     * Used for initializing presenter with some data (from intent, for example)
     */
    protected void initPresenter() {

    }
}
