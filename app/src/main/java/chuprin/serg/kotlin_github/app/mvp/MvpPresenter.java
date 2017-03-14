package chuprin.serg.kotlin_github.app.mvp;

import java.lang.ref.WeakReference;

import chuprin.serg.kotlin_github.app.mvp.view.MvpView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class MvpPresenter<VIEW extends MvpView> {
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private WeakReference<VIEW> view;
    private boolean viewAttached;

    public final void attachView(VIEW view) {
        if (!viewAttached) {
            this.view = new WeakReference<>(view);
            viewAttached = true;
            onViewAttached();
        }
    }

    public final void detachView() {
        if (view != null) {
            view.clear();
            view = null;
            viewAttached = false;

            subscriptions.clear();
        }
    }

    final protected VIEW getView() {
        return view == null ? null : view.get();
    }

    protected void onViewAttached() {

    }

    final public void subscribe(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
