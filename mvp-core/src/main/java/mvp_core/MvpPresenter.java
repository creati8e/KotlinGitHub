package mvp_core;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mvp_core.view.MvpView;
import mvp_core.view.NullObjectView;

public abstract class MvpPresenter<VIEW extends MvpView> {
    private CompositeDisposable viewSubscription = new CompositeDisposable();
    private NullObjectView<VIEW> view = new NullObjectView<>();
    private boolean viewAttached;

    final void attachView(VIEW view) {
        if (!viewAttached) {
            this.view.setView(view);
            viewAttached = true;
            onViewAttached();
        }
    }

    final void detachView() {
        view.removeView();
        viewAttached = false;
        unsubscribeAll();
    }

    final protected VIEW getView() {
        return view.get();
    }

    protected void onViewAttached() {

    }

    final protected void unsubscribeAll() {
        viewSubscription.clear();
    }

    final protected void subscribeView(Disposable disposable) {
        viewSubscription.add(disposable);
    }
}
