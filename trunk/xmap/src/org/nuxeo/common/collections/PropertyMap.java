/*
 * (C) Copyright 2007 Nuxeo SAS <http://nuxeo.com> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.common.collections;

import java.util.Collection;
import java.util.Hashtable;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class PropertyMap {

    private final Hashtable<String, Property> properties = new Hashtable<String, Property>();

    public String get(String name) {
        Property prop = properties.get(name);
        if (prop != null) {
            return prop.getValue();
        }
        return null;
    }

    public String get(String name, String defValue) {
        Property prop = properties.get(name);
        if (prop != null) {
            return prop.getValue();
        }
        return defValue;
    }

    public Property getProperty(String name) {
        return properties.get(name);
    }

    public void set(String name, String value) {
        properties.put(name, new Property(name, value));
    }

    public Collection<Property> getProperties() {
        return properties.values();
    }

    public void clear() {
        properties.clear();
    }

    public String expandVars(String expression) {
        char[] buf = expression.toCharArray();
        StringBuffer result = new StringBuffer(buf.length);
        StringBuffer varBuf = new StringBuffer();
        boolean dollar = false;
        boolean var = false;
        for (char c : buf) {
            switch (c) {
            case '$':
                dollar = true;
                break;
            case '{':
                if (dollar) {
                    dollar = false;
                    var = true;
                }
                break;
            case '}':
                if (var) {
                    var = false;
                    String varName = varBuf.toString();
                    String varValue = get(varName); // get the variable value
                    if (varValue != null) {
                        result.append(varValue);
                    } else { // let the variable as is
                        result.append("${").append(varName).append('}');
                    }
                }
                break;
            default:
                if (var) {
                    varBuf.append(c);
                } else {
                    result.append(c);
                }
                break;
            }
        }
        return result.toString();
    }

    public class Property {

        private final String name;
        private String value;

        public Property(String name) {
            this(name, null);
        }

        public Property(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getRawValue() {
            return value;
        }

        public String getValue() {
            return expandVars(value);
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        PropertyMap props = new PropertyMap();

        props.set("var1", "test");
        props.set("vvar1", "var1");
        props.set("var2", "${var1}");
        props.set("var3", "==${var1}==");
        // props.set("var4", "==${${vvar1}}==");

        for (Property p : props.getProperties()) {
            System.out.println("> " + p.getName() + " = \"" + p.getValue()
                    + '"');// ["+p.getRawValue()+"]");
        }
    }

}
