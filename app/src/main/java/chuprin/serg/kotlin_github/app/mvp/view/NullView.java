package chuprin.serg.kotlin_github.app.mvp.view;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class NullView {

    @SuppressWarnings("unchecked")
    static <T> T of(Class<T> clazz) {
        Class<?> interfaceClass = null;
        for (Class<?> aClass : clazz.getInterfaces()) {
            if (isSubTypeOfMvpView(aClass)) {
                interfaceClass = aClass;
                break;
            }
        }
        if (interfaceClass == null) {
            throw new IllegalStateException("View should implement interface" +
                    " inherited from MvpView");
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new DefaultValueInvocationHandler());
    }

    private static boolean isSubTypeOfMvpView(Class<?> clazz) {
        if (clazz.equals(MvpView.class)) {
            return true;
        }
        Class[] superInterfaces = clazz.getInterfaces();
        for (Class superInterface : superInterfaces) {
            if (isSubTypeOfMvpView(superInterface)) {
                return true;
            }
        }
        return false;
    }

    private static class DefaultValueInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return Defaults.defaultValue(method.getReturnType());
        }
    }
}