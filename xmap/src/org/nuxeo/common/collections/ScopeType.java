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
 * $Id: ScopeType.java 17516 2007-04-22 17:00:59Z sfermigier $
 */

package org.nuxeo.common.collections;

/**
 * Scope type definitions for a scoped map.
 * <p>
 * Only request and default scopes are defined for now, but others may be added.
 * 
 * @see ScopedMap
 * 
 * @author <a href="mailto:at@nuxeo.com">Anahide Tchertchian</a>
 * 
 */
public enum ScopeType {
    DEFAULT, REQUEST;

    public String getScopedKey(String key) {
        return getScopePrefix() + key;
    }

    public String getScopePrefix() {
        return name().toLowerCase() + '/';
    }

}
