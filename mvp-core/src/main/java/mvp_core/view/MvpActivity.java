package mvp_core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mvp_core.ComponentHolder;
import mvp_core.MvpPresenter;
import mvp_core.PresenterHelper;


public abstract class MvpActivity<PRESENTER extends MvpPresenter>
        extends AppCompatActivity
        implements MvpView, ComponentHolder {

    private PresenterHelper<PRESENTER> helper;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.resume();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(getLayoutRes());
        compositeDisposable = new CompositeDisposable();
        helper = new PresenterHelper<>(this, state);
        helper.attachView();
    }

    protected abstract int getLayoutRes();

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

    protected void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected PRESENTER getPresenter() {
        return helper.getPresenter();
    }

}
