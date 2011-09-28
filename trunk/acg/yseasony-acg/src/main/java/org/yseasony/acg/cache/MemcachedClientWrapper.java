package org.yseasony.acg.cache;

import java.io.IOException;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.buffer.SimpleBufferAllocator;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.TokyoTyrantTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

/**
 * 
 * @author yseasony
 * 
 */
@SuppressWarnings("deprecation")
public class MemcachedClientWrapper {

	private final Log log = LogFactory.getLog(MemcachedCache.class);

	private static Map<String, Object> configuration = MemcachedConfigurationBuilder
			.getInstance().parseConfiguration();

	private static MemcachedClientBuilder builder;

	private static MemcachedClient client;
	
	private static MemcachedClientWrapper INSTANCE = new MemcachedClientWrapper();

	private MemcachedClientWrapper() {
		builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(String
				.valueOf(configuration
						.get(MemcachedConfigurationBuilder.SERVERS))));
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		builder.setCommandFactory(new BinaryCommandFactory());
		builder.setTranscoder(new TokyoTyrantTranscoder());
		builder.setBufferAllocator(new SimpleBufferAllocator());
		builder.setConnectionPoolSize(Integer.parseInt((String) configuration
				.get(MemcachedConfigurationBuilder.POOL_SIZE)));
		try {
			client = builder.build();
		} catch (IOException e) {
			log.error("MemcachedClient build error ", e);
		}
	}

	public MemcachedClient getClient() {
		return client;
	}
	
	public static MemcachedClientWrapper getInstance() {
		return INSTANCE;
	}
	
	public int getExpiration(){
		return Integer.parseInt(String.valueOf(configuration.get(MemcachedConfigurationBuilder.EXPIRATION)));
	}
}
