package org.nuxeo.common.xmap;

import java.lang.reflect.Method;

public class XMethodGetter implements XGetter {
    private Method method;
    private Class<?> clazz;
    private String name;

    public XMethodGetter(Method method, Class<?> clazz, String name) {
        this.method = method;
        this.clazz = clazz;
        this.name = name;
    }

    public Class<?> getType() {
        if (this.method == null) {
            throw new IllegalArgumentException("no such getter method: "
                    + this.clazz.getName() + '.' + this.name);
        }

        return this.method.getReturnType();
    }

    public Object getValue(Object instance) throws Exception {
        if (this.method == null) {
            throw new IllegalArgumentException("no such getter method: "
                    + this.clazz.getName() + '.' + this.name);
        }

        if (instance == null) {
            return null;
        }
        return this.method.invoke(instance, new Object[0]);
    }
}
