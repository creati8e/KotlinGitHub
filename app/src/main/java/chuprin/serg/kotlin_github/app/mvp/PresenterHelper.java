package chuprin.serg.kotlin_github.app.mvp;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import chuprin.serg.kotlin_github.app.mvp.cache.PresenterCache;
import chuprin.serg.kotlin_github.app.mvp.view.MvpView;

@SuppressWarnings("unchecked")
public class PresenterHelper<PRESENTER extends MvpPresenter> {
    private final PresenterCache presenterCache = PresenterCache.INSTANCE;
    private final MvpView view;
    private boolean isRecreating;
    private PRESENTER presenter;

    public PresenterHelper(MvpView view, Object component) {
        this.view = view;
        inject(view, component);
        for (Field field : view.getClass().getDeclaredFields()) {
            if (field.getType().getSuperclass().isAssignableFrom(MvpPresenter.class)) {
                try {
                    presenter = (PRESENTER) field.get(view);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop(boolean retainPresenter) {
        presenter.detachView();
        if (!isRecreating && !retainPresenter) {
            presenterCache.delete(presenter);
        }
    }

    public void saveState(Bundle bundle) {
        isRecreating = true;
        if (bundle != null) {
            presenterCache.save(bundle, presenter);
        }
    }

    public void attachView() {
        presenter.attachView(view);
    }

    private void inject(MvpView view, Object component) {
        try {
            Class viewClass = view.getClass();
            Class componentClass = component.getClass();
            Method[] methods = componentClass.getMethods();
            for (Method method : methods) {
                Class types[] = method.getParameterTypes();
                if (method.getName().startsWith("inject") &&
                        types != null && types.length == 1 &&
                        types[0].isAssignableFrom(viewClass)) {
                    method.invoke(component, view);
                    return; // all ok
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}


