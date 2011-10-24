package org.yseasony.acg.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Converter from the Config to a proper
 * 
 * @author yseasony
 * 
 */
final class MemcachedConfigurationBuilder {

	static final String SERVERS = "memcached.servers";
	static final String POOL_SIZE = "memcached.poolSize";
	static final String EXPIRATION = "memcached.expiration";

	/**
	 * This class instance.
	 */
	private static final String MEMCACHED_RESOURCE = "/memcached.properties";

	private static Map<String, Object> WRITERS = new HashMap<String, Object>();

	private static MemcachedConfigurationBuilder INSTANCE = new MemcachedConfigurationBuilder();

	public static MemcachedConfigurationBuilder getInstance() {
		return INSTANCE;
	}

	/**
	 * Hidden constructor, this class can't be instantiated.
	 */
	private MemcachedConfigurationBuilder() {
		WRITERS.put(SERVERS, "localhost:11211");
		WRITERS.put(POOL_SIZE, 5);
		WRITERS.put(EXPIRATION, 30 * 60);
	}

	/**
	 * Pares the Config and builds a new
	 * 
	 * @return
	 */
	public Map<String, Object> parseConfiguration() {
		Properties config = new Properties();
		// load the properties specified from /memcached.properties, if present
		InputStream input = this.getClass().getResourceAsStream(
				MEMCACHED_RESOURCE);
		if (input != null) {
			try {
				config.load(input);
			} catch (IOException e) {
				throw new RuntimeException(
						"An error occurred while reading classpath property '"
								+ MEMCACHED_RESOURCE
								+ "', see nested exceptions", e);
			} finally {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}

		Set<Entry<Object, Object>> properties = config.entrySet();
		for (Entry<Object, Object> entry : properties) {
			WRITERS.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return WRITERS;
	}

}
