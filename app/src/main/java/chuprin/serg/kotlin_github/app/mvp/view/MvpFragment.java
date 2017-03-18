package chuprin.serg.kotlin_github.app.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chuprin.serg.kotlin_github.app.mvp.MvpPresenter;
import chuprin.serg.kotlin_github.app.mvp.PresenterHelper;

public abstract class MvpFragment<PRESENTER extends MvpPresenter> extends Fragment
        implements MvpView {

    @LayoutRes
    private final int layoutRes;
    private PresenterHelper<PRESENTER> helper;

    public MvpFragment(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutRes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedState) {
        super.onViewCreated(view, savedState);
        helper = new PresenterHelper<>(this, createComponent(savedState));
        initPresenter();
        helper.attachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        helper.saveState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.stop(getActivity().isChangingConfigurations());
        helper = null;
    }

    protected void initPresenter() {

    }

    protected abstract Object createComponent(@Nullable Bundle savedState);
}
