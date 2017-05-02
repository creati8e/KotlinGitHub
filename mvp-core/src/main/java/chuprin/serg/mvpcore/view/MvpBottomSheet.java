package chuprin.serg.mvpcore.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;

import chuprin.serg.mvpcore.MvpPresenter;
import chuprin.serg.mvpcore.PresenterHelper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class MvpBottomSheet<PRESENTER extends MvpPresenter> extends BottomSheetDialogFragment
        implements MvpView {

    private PresenterHelper<PRESENTER> helper;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
        helper = new PresenterHelper<>(this, createComponent(savedInstanceState));
        helper.attachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        helper.saveState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mCompositeDisposable.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        helper.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.stop(getActivity().isChangingConfigurations());
        helper = null;
    }

    @SuppressWarnings("unused")
    protected void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected abstract Object createComponent(@Nullable Bundle savedState);
}
