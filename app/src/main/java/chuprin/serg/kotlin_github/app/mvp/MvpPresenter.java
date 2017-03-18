package chuprin.serg.kotlin_github.app.mvp;

import chuprin.serg.kotlin_github.app.mvp.view.MvpView;
import chuprin.serg.kotlin_github.app.mvp.view.NullObjectView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class MvpPresenter<VIEW extends MvpView> {
    private CompositeSubscription viewSubscription = new CompositeSubscription();
    private NullObjectView<VIEW> view = new NullObjectView<>();
    private boolean viewAttached;

    final void attachView(VIEW view) {
        if (!viewAttached) {
            this.view.setView(view);
            viewAttached = true;
            onViewAttached();
        }
    }

    protected final void detachView() {
        view.removeView();
        viewAttached = false;
        viewSubscription.clear();
    }

    final protected VIEW getView() {
        return view.get();
    }

    protected void onViewAttached() {

    }

    final public void subscribeView(Subscription subscription) {
        viewSubscription.add(subscription);
    }
}
