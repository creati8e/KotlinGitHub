package mvp_core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mvp_core.ComponentHolder;
import mvp_core.MvpPresenter;
import mvp_core.PresenterHelper;


public abstract class MvpBottomSheet<PRESENTER extends MvpPresenter> extends BottomSheetDialogFragment
        implements MvpView, ComponentHolder {

    private PresenterHelper<PRESENTER> helper;
    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        helper = new PresenterHelper<>(this, savedInstanceState);
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

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        helper.resume();
    }

    @SuppressWarnings("unused")
    protected void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

}
