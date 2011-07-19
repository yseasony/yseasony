package org.nuxeo.common.xmap;

import java.lang.reflect.Field;

public class XFieldGetter implements XGetter {
    private Field field;

    public XFieldGetter(Field field) {
        this.field = field;
        this.field.setAccessible(true);
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Object getValue(Object instance) throws Exception {
        if (instance == null) {
            return null;
        }
        return this.field.get(instance);
    }
}
