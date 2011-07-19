package org.nuxeo.common.utils.i18n;

import java.io.Serializable;

public class Labeler implements Serializable {
    private static final long serialVersionUID = -4139432411098427880L;

    protected final String prefix;

    public Labeler(String prefix) {
        this.prefix = prefix;
    }

    protected static String unCapitalize(String s) {
        char c = Character.toLowerCase(s.charAt(0));
        return c + s.substring(1);
    }

    public String makeLabel(String itemId) {
        return prefix + '.' + unCapitalize(itemId);
    }

}
