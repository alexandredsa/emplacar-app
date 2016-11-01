package br.com.fatecpg.emplacar.view.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {

    public static Object instantiate(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = getClass(className);
        Constructor<?> ctor = clazz.getConstructor(String.class);
        return ctor.newInstance();
    }

    public static Class getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
