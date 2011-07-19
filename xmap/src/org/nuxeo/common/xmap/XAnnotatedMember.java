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

import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class XAnnotatedMember {

    public final XMap xmap;
    public final XSetter setter;
    public final XGetter getter;
    public Path path;
    public boolean trim;

    // the java type of the described element
    public Class<?> type;
    // not null if the described object is an xannotated object
    public XAnnotatedObject xao;
    // the value factory used to transform strings in objects compatible
    // with this member type
    // In the case of collection types this factory is
    // used for collection components
    public XValueFactory valueFactory;

    public boolean cdata;

    protected XAnnotatedMember(XMap xmap, XSetter setter, XGetter getter) {
        this.xmap = xmap;
        this.setter = setter;
        this.getter = getter;
    }

    public XAnnotatedMember(XMap xmap, XSetter setter, XGetter getter,
            XNode anno) {
        this.xmap = xmap;
        this.setter = setter;
        this.getter = getter;
        this.path = new Path(anno.value());
        this.trim = anno.trim();
        this.type = setter.getType();
        this.valueFactory = xmap.getValueFactory(this.type);
        this.xao = xmap.register(this.type);
    }

    protected void setValue(Object instance, Object value) throws Exception {
        setter.setValue(instance, value);
    }

    public void process(Context ctx, Element element) throws Exception {
        Object value = getValue(ctx, element);
        if (value != null) {
            setValue(ctx.getObject(), value);
        }
    }

    protected Object getValue(Context ctx, Element base) throws Exception {
        if (xao != null) {
            Element el = (Element) DOMHelper.getElementNode(base, path);
            return el == null ? null : xao.newInstance(ctx, el);
        }
        // scalar field
        if (type == Element.class) {
            // allow DOM elements as values
            return base;
        }
        String val = DOMHelper.getNodeValue(base, path);
        if (val != null) {
            if (trim) {
                val = val.trim();
            }
            return valueFactory.getValue(ctx, val);
        }
        return null;
    }

    public void decode(Object instance, Node base, Document document,
            List<String> filters) throws Exception {
        if (!isFilter(filters)) {
            return;
        }

        Object object = this.getter.getValue(instance);
        if (object == null) {
			return;
		}
        
        Node node = base;

        int len = this.path.segments.length;
        for (int i = 0; i < len; ++i) {
            Node n = DOMHelper.getElementNode(node, this.path.segments[i]);

            if (n == null) {
                Element element = document.createElement(this.path.segments[i]);
                node = node.appendChild(element);
            } else {
                node = n;
            }

        }

        if ((object != null)
                && (Element.class.isAssignableFrom(object.getClass()))) {
            return;
        }

        if (this.xao != null) {
            this.xao.decode(object, node, document, filters);
        } else {
            String value = (object == null) ? "" : object.toString();

            if ((this.path.attribute != null)
                    && (this.path.attribute.length() > 0)) {
                Attr attr = document.createAttribute(this.path.attribute);
                attr.setNodeValue(value);

                ((Element) node).setAttributeNode(attr);
            } else if (this.cdata) {
                CDATASection cdataSection = document.createCDATASection(value);
                node.appendChild(cdataSection);
            } else {
                node.setTextContent(value);
            }
        }
    }

    protected boolean isFilter(List<String> filters) {
        boolean filter = false;

        if ((filters == null) || (filters.size() == 0))
            filter = true;
        else {
            filter = filters.contains(this.path.path);
        }

        return filter;
    }
}
