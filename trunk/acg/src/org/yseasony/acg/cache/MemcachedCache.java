/*
 *    Copyright 2009-2010 The iBaGuice Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.yseasony.acg.cache;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

public final class MemcachedCache implements Cache {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Log log = LogFactory.getLog(MemcachedCache.class);
	private final static MemcachedClient CLIENT = MemcachedClientWrapper
			.getInstance().getClient();
	private final static int EXPIRATION = MemcachedClientWrapper.getInstance()
			.getExpiration();

	/**
	 * The cache id.
	 */
	private final String id;

	private final static String KEY_PREFIX = "_acg_";

	private String toKeyString(final Object key) {
		String keyString = KEY_PREFIX + Integer.toHexString(key.hashCode());
		if (log.isDebugEnabled()) {
			log.debug("Object key '" + key + "' converted in '" + keyString
					+ "'");
		}
		return keyString;
	}

	public MemcachedCache(final String id) {
		this.id = id;
	}

	public void clear() {
		try {
			CLIENT.decr(id, 1);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public Object getObject(Object key) {
		String keyString = toKeyString(key);
		Object ret = null;
		try {
			ret = CLIENT.get(keyString);
			if (this.log.isDebugEnabled()) {
				this.log.debug("Retrived object (" + keyString + ", " + ret
						+ ")");
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public int getSize() {
		return Integer.MAX_VALUE;
	}

	public void putObject(Object key, Object value) {
		String keyString = toKeyString(key);
		try {
			CLIENT.set(keyString, EXPIRATION, value);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}

	}

	public Object removeObject(Object key) {
		String keyString = toKeyString(key);

		if (log.isDebugEnabled()) {
			log.debug("Removing object '" + keyString + "'");
		}

		Object result = this.getObject(keyString);
		if (result != null) {
			try {
				CLIENT.delete(keyString);
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
