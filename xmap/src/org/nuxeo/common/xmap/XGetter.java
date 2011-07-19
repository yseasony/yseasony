package org.nuxeo.common.xmap;

public abstract interface XGetter {
    public abstract Class<?> getType();

    public abstract Object getValue(Object paramObject) throws Exception;
}
