/*
 * (C) Copyright 2006-2007 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.common.xmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class XAnnotatedList extends XAnnotatedMember {

    protected static final ElementVisitor elementListVisitor = new ElementVisitor();
    protected static final ElementValueVisitor elementVisitor = new ElementValueVisitor();
    protected static final AttributeValueVisitor attributeVisitor = new AttributeValueVisitor();

    // indicates the type of the collection components
    public Class<?> componentType;

    protected XAnnotatedList(XMap xmap, XSetter setter, XGetter getter) {
        super(xmap, setter, getter);
    }

    public XAnnotatedList(XMap xmap, XSetter setter, XGetter getter,
            XNodeList anno) {
        super(xmap, setter, getter);
        path = new Path(anno.value());
        trim = anno.trim();
        type = anno.type();
        componentType = anno.componentType();
        valueFactory = xmap.getValueFactory(componentType);
        xao = xmap.register(componentType);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object getValue(Context ctx, Element base) throws Exception {
        ArrayList<Object> values = new ArrayList<Object>();
        if (xao != null) {
            DOMHelper.visitNodes(ctx, this, base, path, elementListVisitor,
                    values);
        } else {
            if (path.attribute != null) {
                // attribute list
                DOMHelper.visitNodes(ctx, this, base, path, attributeVisitor,
                        values);
            } else {
                // element list
                DOMHelper.visitNodes(ctx, this, base, path, elementVisitor,
                        values);
            }
        }

        if (type != ArrayList.class) {
            if (type.isArray()) {
                if (componentType.isPrimitive()) {
                    // primitive arrays cannot be casted to Object[]
                    return PrimitiveArrays.toPrimitiveArray(values,
                            componentType);
                } else {
                    return values.toArray((Object[]) Array.newInstance(
                            componentType, values.size()));
                }
            } else {
                Collection<Object> col = (Collection<Object>) type
                        .newInstance();
                col.addAll(values);
                return col;
            }
        }

        return values;
    }
}

class ElementVisitor extends DOMHelper.NodeVisitor {
    @Override
    public void visitNode(Context ctx, XAnnotatedMember xam, Node node,
            Collection<Object> result) {
        try {
            result.add(xam.xao.newInstance(ctx, (Element) node));
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
    }
}

class ElementValueVisitor extends DOMHelper.NodeVisitor {
    @Override
    public void visitNode(Context ctx, XAnnotatedMember xam, Node node,
            Collection<Object> result) {
        String val = node.getTextContent();
        if (xam.trim) {
            val = val.trim();
        }
        if (xam.valueFactory != null) {
            result.add(xam.valueFactory.getValue(ctx, val));
        } else {
            // TODO: log warning?
            result.add(val);
        }
    }
}

class AttributeValueVisitor extends DOMHelper.NodeVisitor {
    @Override
    public void visitNode(Context ctx, XAnnotatedMember xam, Node node,
            Collection<Object> result) {
        String val = node.getNodeValue();
        if (xam.valueFactory != null) {
            result.add(xam.valueFactory.getValue(ctx, val));
        } else {
            // TODO: log warning?
            result.add(val);
        }
    }
}
