package moe.tree.eduvod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:vodconfig.properties")
public class ConstantPropertiesUtil implements InitializingBean {

	@Value("${aliyun.vod.keyid}")
	private String keyId;
	@Value("${aliyun.vod.keysecret}")
	private String keySecret;
	@Value("${aliyun.vod.regionId}")
	private String regionId;
	@Value("${aliyun.vod.storageLocation}")
	private String storageLocation;
	public static String ACCESS_KEY_ID;
	public static String ACCESS_KEY_SECRET;

	public static String REGION_ID;

	public static String STORAGE_LOCATION;

	@Override
	public void afterPropertiesSet() throws Exception {
		ACCESS_KEY_ID = keyId;
		ACCESS_KEY_SECRET = keySecret;
		REGION_ID = regionId;
		STORAGE_LOCATION = storageLocation;
	}
}