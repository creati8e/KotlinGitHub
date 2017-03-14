package chuprin.serg.kotlin_github.app.mvp.view;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import chuprin.serg.kotlin_github.app.mvp.ComponentCache;
import chuprin.serg.kotlin_github.app.mvp.ComponentCacheProvider;
/*
 * Copyright (C) 2017 Renat Sarymsakov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MvpViewHelper<VIEW extends MvpView> {

    private static final String INJECT_METHOD_NAME = "inject";
    private static final String TAG = MvpViewHelper.class.getSimpleName();
    private VIEW view;

    public MvpViewHelper(@NonNull VIEW view) {
        this.view = view;
    }

    public void onCreate() {
        Object component = getComponentCache().getComponent(view);

        if (component == null) {
            return;
        }
        Class clientClass = view.getClass();

        try {

            Class componentClass = component.getClass();

            Log.d(TAG, String.format(
                    "onCreate: looking for '%s' methods in [%s] for [%s]",
                    INJECT_METHOD_NAME, componentClass.getSimpleName(), clientClass.getSimpleName()
            ));

            for (Method method : componentClass.getMethods()) {
                Class types[] = method.getParameterTypes();
                if (method.getName().startsWith(INJECT_METHOD_NAME) &&
                        types != null && types.length == 1 &&
                        types[0].isAssignableFrom(clientClass)) {
                    method.invoke(component, view);
                    Log.d(TAG, String.format(
                            "onCreate: client [%s] was injected by [%s] with [%s]",
                            clientClass.getSimpleName(), componentClass.getSimpleName(), method.getName()
                    ));
                    return; // all ok
                }
            }

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        Log.w(TAG, "No inject methods for client [" + clientClass.getSimpleName() + "]");
    }

    public void onDestroy(boolean retainComponent) {
        getComponentCache().stop(view, retainComponent);
    }

    private ComponentCache getComponentCache() {
        return ((ComponentCacheProvider) view.getContext().getApplicationContext()).getComponentCache();
    }

}
