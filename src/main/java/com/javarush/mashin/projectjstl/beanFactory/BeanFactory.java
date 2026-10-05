package com.javarush.mashin.projectjstl.beanFactory;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
	private BeanFactory() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	private static final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

	public static synchronized <T> T getBean(Class<T> clazz) {
		if (!beanMap.containsKey(clazz)) {
			Constructor<?> constructor = clazz.getConstructors()[0];
			constructor.setAccessible(true);
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			Object[] parameters = new Object[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				parameters[i] = beanMap.get(parameterTypes[i]);
			}

			try {
				beanMap.put(clazz, constructor.newInstance(parameters));
			} catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
				throw new ProjectJSTLException("Create bean exception", e);
			}
		}
		return (T) beanMap.get(clazz);
	}

}
