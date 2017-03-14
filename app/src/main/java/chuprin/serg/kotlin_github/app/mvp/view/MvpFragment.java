package chuprin.serg.kotlin_github.app.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chuprin.serg.kotlin_github.app.mvp.MvpPresenter;

public abstract class MvpFragment<PRESENTER extends MvpPresenter> extends Fragment
        implements MvpView<PRESENTER> {

    private MvpViewHelper<MvpFragment<PRESENTER>> helper;
    private boolean mRecreating;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        getPresenter().attachView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new MvpViewHelper<>(this);
        helper.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
        helper.onDestroy(mRecreating || getActivity().isChangingConfigurations());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mRecreating = true;
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
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
