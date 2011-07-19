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

import java.util.HashMap;
import java.util.Map;

import org.nuxeo.common.xmap.annotation.XNodeMap;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class XAnnotatedMap extends XAnnotatedList {

    protected static final ElementMapVisitor elementMapVisitor = new ElementMapVisitor();
    protected static final ElementValueMapVisitor elementVisitor = new ElementValueMapVisitor();
    protected static final AttributeValueMapVisitor attributeVisitor = new AttributeValueMapVisitor();

    protected final Path key;

    public XAnnotatedMap(XMap xmap, XSetter setter, XGetter getter,
            XNodeMap anno) {
        super(xmap, setter, getter);
        path = new Path(anno.value());
        trim = anno.trim();
        key = new Path(anno.key());
        type = anno.type();
        componentType = anno.componentType();
        valueFactory = xmap.getValueFactory(componentType);
        xao = xmap.register(componentType);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object getValue(Context ctx, Element base)
            throws IllegalAccessException, InstantiationException {
        Map<String, Object> values = new HashMap<String, Object>();
        if (xao != null) {
            DOMHelper.visitMapNodes(ctx, this, base, path, elementMapVisitor,
                    values);
        } else {
            if (path.attribute != null) {
                // attribute list
                DOMHelper.visitMapNodes(ctx, this, base, path,
                        attributeVisitor, values);
            } else {
                // element list
                DOMHelper.visitMapNodes(ctx, this, base, path, elementVisitor,
                        values);
            }
        }

        if (type != HashMap.class) {
            Map<String, Object> map = (Map<String, Object>) type.newInstance();
            map.putAll(values);
        }

        return values;
    }

}

class ElementMapVisitor extends DOMHelper.NodeMapVisitor {
    @Override
    public void visitNode(Context ctx, XAnnotatedMember xam, Node node,
            String key, Map<String, Object> result) {
        try {
            result.put(key, xam.xao.newInstance(ctx, (Element) node));
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
    }
}

class ElementValueMapVisitor extends DOMHelper.NodeMapVisitor {
    @Override
    public void visitNode(Context ctx, XAnnotatedMember xam, Node node,
            String key, Map<String, Object> result) {
        String val = node.getTextContent();
        if (xam.trim) {
            val = val.trim();
        }
        if (xam.valueFactory != null) {
            result.put(key, xam.valueFactory.getValue(ctx, val));
        } else {
            // TODO: log warning?
            result.put(key, val);
        }
    }
}

class AttributeValueMapVisitor extends DOMHelper.NodeMapVisitor {
    @Override
    public void visitNode(Context ctx, XAnnotatedMember xam, Node node,
            String key, Map<String, Object> result) {
        String val = node.getNodeValue();
        if (xam.valueFactory != null) {
            result.put(key, xam.valueFactory.getValue(ctx, val));
        } else {
            // TODO: log warning?
            result.put(key, val);
        }
    }
}
