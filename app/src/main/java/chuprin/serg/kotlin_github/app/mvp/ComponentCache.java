package chuprin.serg.kotlin_github.app.mvp;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chuprin.serg.kotlin_github.app.mvp.view.MvpView;
import rx.functions.Func0;

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
public class ComponentCache {

    private final List<ComponentEntry> components = new ArrayList<>();

    public final Object getComponent(@NonNull MvpView view) {
        ComponentEntry entry = findComponentEntryByClientClass(view.getClass());
        if (entry == null) {
            return null;
        }
        if (entry.component == null) {
            entry.component = entry.componentFactory.call();
        }
        if (entry.clients.contains(view)) {
            throw new IllegalStateException(view + " is already attached");
        }
        entry.clients.add(view);
        return entry.component;
    }

    @SafeVarargs
    protected final void register(@NonNull Func0<Object> componentFactory, Class<? extends MvpView>... clientClasses) {

        if (clientClasses.length == 0) {
            throw new IllegalArgumentException("No classes specified");
        }

        for (Class<? extends MvpView> clientClass : clientClasses) {
            if (findComponentEntryByClientClass(clientClass) != null) {
                throw new IllegalArgumentException(clientClass.getName() + " is already registered");
            }
        }
        components.add(new ComponentEntry(Arrays.asList(clientClasses), componentFactory));
    }
    //region internal

    public void stop(@NonNull MvpView client, boolean retainComponent) {
        ComponentEntry entry = findComponentEntryByClientClass(client.getClass());
        if (entry == null) {
            return;
        }
        List<MvpView> clients = entry.clients;
        if (clients.remove(client)) {
            if (!retainComponent && clients.isEmpty()) {
                entry.component = null;
            }
        } else {
            throw new IllegalStateException(client + " is already detached");
        }
    }


    private ComponentEntry findComponentEntryByClientClass(@NonNull Class<? extends MvpView> clazz) {
        for (ComponentEntry componentEntry : components) {
            for (Class<? extends MvpView> clientClass : componentEntry.viewClasses) {
                if (clientClass.isAssignableFrom(clazz)) {
                    return componentEntry;
                }
            }
        }
        return null;
    }

    //endregion

    private static class ComponentEntry {

        final List<? extends Class<? extends MvpView>> viewClasses;
        final Func0<Object> componentFactory;
        final List<MvpView> clients = new ArrayList<>();
        Object component;

        private ComponentEntry(List<? extends Class<? extends MvpView>> viewClasses, Func0<Object> componentFactory) {
            this.viewClasses = viewClasses;
            this.componentFactory = componentFactory;
        }
    }
}
