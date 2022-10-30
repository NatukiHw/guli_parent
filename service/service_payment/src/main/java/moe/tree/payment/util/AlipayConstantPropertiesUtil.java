package moe.tree.payment.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:alipayConfig.properties")
public class AlipayConstantPropertiesUtil implements InitializingBean {

	@Value("${alipay.appId}")
	private String appId;

	@Value("${alipay.appPublicKey}")
	private String appPublicKey;

	@Value("${alipay.alipayPublicKey}")
	private String alipayPublicKey;

	@Value("${alipay.privateKey}")
	private String privateKey;

	@Value("${alipay.notifyUrl}")
	private String notifyUrl;

	@Value("${alipay.returnUrl}")
	private String returnUrl;

	@Value("${alipay.signType}")
	private String signType;

	@Value("${alipay.charset}")
	private String charset;

	@Value("${alipay.gatewayUrl}")
	private String gatewayUrl;

	public static String APP_ID;
	public static String APP_PUBLIC_KEY;
	public static String ALIPAY_PUBLIC_KEY;
	public static String PRIVATE_KEY;
	public static String NOTIFY_URL;
	public static String RETURN_URL;
	public static String SIGN_TYPE;
	public static String CHARSET;
	public static String GATEWAY_URL;

	@Override
	public void afterPropertiesSet() throws Exception {
		APP_ID = appId;
		APP_PUBLIC_KEY = appPublicKey;
		ALIPAY_PUBLIC_KEY = alipayPublicKey;
		PRIVATE_KEY = privateKey;
		NOTIFY_URL = notifyUrl;
		RETURN_URL = returnUrl;
		SIGN_TYPE = signType;
		CHARSET = charset;
		GATEWAY_URL = gatewayUrl;
	}
}
