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

import org.nuxeo.common.xmap.annotation.XContent;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import java.io.IOException;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class XAnnotatedContent extends XAnnotatedMember {

    private static final OutputFormat DEFAULT_FORMAT = new OutputFormat();

    static {
        DEFAULT_FORMAT.setOmitXMLDeclaration(true);
        DEFAULT_FORMAT.setIndenting(true);
        DEFAULT_FORMAT.setMethod("xml");
        DEFAULT_FORMAT.setEncoding("UTF-8");
    }

    public XAnnotatedContent(XMap xmap, XSetter setter, XGetter getter,
            XContent anno) {
        super(xmap, setter, getter);
        this.path = new Path(anno.value());
        this.type = setter.getType();
        this.valueFactory = xmap.getValueFactory(this.type);
        this.xao = xmap.register(this.type);
    }

    @Override
    protected Object getValue(Context ctx, Element base) throws IOException {
        Element el = (Element) DOMHelper.getElementNode(base, path);
        if (el == null) {
            return null;
        }
        el.normalize();
        Node node = el.getFirstChild();
        if (node == null) {
            return "";
        }
        Range range = ((DocumentRange) el.getOwnerDocument()).createRange();
        range.setStartBefore(node);
        range.setEndAfter(el.getLastChild());
        DocumentFragment fragment = range.cloneContents();
        boolean asDOM = setter.getType() == DocumentFragment.class;
        return asDOM ? fragment : DOMSerializer.toString(fragment,
                DEFAULT_FORMAT);
    }

}
