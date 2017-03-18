package chuprin.serg.kotlin_github.app.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import chuprin.serg.kotlin_github.app.mvp.MvpPresenter;
import chuprin.serg.kotlin_github.app.mvp.PresenterHelper;


public abstract class MvpActivity<PRESENTER extends MvpPresenter>
        extends AppCompatActivity
        implements MvpView {

    @LayoutRes
    private final int layoutRes;
    private PresenterHelper<PRESENTER> helper;

    protected MvpActivity(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(layoutRes);
        helper = new PresenterHelper<>(this, createComponent(state));
        initPresenter();
        helper.attachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.stop(isChangingConfigurations());
        helper = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        helper.saveState(outState);
    }

    protected abstract Object createComponent(@Nullable Bundle state);

    /**
     * Used for initializing presenter with some data (from intent, for example)
     * before view attached
     */

    protected void initPresenter() {

    }
}
