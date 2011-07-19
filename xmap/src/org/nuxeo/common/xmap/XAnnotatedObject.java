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

import java.util.*;

import org.nuxeo.common.xmap.annotation.XObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class XAnnotatedObject {

    final XMap xmap;
    final Class<?> klass;
    final Path path;

    final List<XAnnotatedMember> members;

    Sorter sorter;
    Sorter deSorter;

    public XAnnotatedObject(XMap xmap, Class<?> klass, XObject xob) {
        this.xmap = xmap;
        this.klass = klass;
        path = new Path(xob.value());
        members = new ArrayList<XAnnotatedMember>();
        String[] order = xob.order();
        if (order.length > 0) {
            sorter = new Sorter(order);
        }
    }

    public void addMember(XAnnotatedMember member) {
        members.add(member);
    }

    public Path getPath() {
        return path;
    }

    public Object newInstance(Context ctx, Element element) throws Exception {
        Object ob = klass.newInstance();
        ctx.push(ob);

        if (sorter != null) {
            Collections.sort(members, sorter);
            sorter = null; // sort only once
        }

        // set annotated members
        for (XAnnotatedMember member : members) {
            member.process(ctx, element);
        }

        return ctx.pop();
    }

    public void decode(Object instance, Node base, Document document,
            List<String> filters) throws Exception {
        Node node = base;
        String name = this.path.path;

        if (this.sorter != null) {
            this.deSorter = this.sorter;
        }

        if (this.deSorter != null) {
            Collections.sort(this.members, this.deSorter);
            this.deSorter = null;
        }

        if ((name != null) && (name.length() > 0)) {
            Element element = document.createElement(name);
            node = node.appendChild(element);
        }

        for (XAnnotatedMember annotatedMember : this.members)
            annotatedMember.decode(instance, node, document, filters);
    }
}

class Sorter implements Comparator<XAnnotatedMember> {

    private final Map<String, Integer> order = new HashMap<String, Integer>();

    Sorter(String[] order) {
        for (int i = 0; i < order.length; i++) {
            this.order.put(order[i], i);
        }
    }

    public int compare(XAnnotatedMember o1, XAnnotatedMember o2) {
        Integer order1 = order.get(o1.path.path);
        Integer order2 = order.get(o2.path.path);
        int n1 = order1 == null ? Integer.MAX_VALUE : order1;
        int n2 = order2 == null ? Integer.MAX_VALUE : order2;
        return n1 - n2;
    }

}
