package moe.tree.sms.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@PropertySource("classpath:mailconfig.properties")
public class ConstantPropertiesUtil implements InitializingBean {

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.host}")
	private String host;

	@Value("${mail.port}")
	private Integer port;

	@Value("${mail.auth}")
	private boolean auth;

	@Value("${mail.useSsl}")
	private boolean useSsl;

	@Value("${mail.protocol}")
	private String protocol;

	public static String USERNAME;

	public static String PASSWORD;

	public static String HOST;

	public static Integer PORT;

	public static String PROTOCOL;

	public static Properties PROPERTIES;


	@Override
	public void afterPropertiesSet() throws Exception {
		USERNAME = username;
		PASSWORD = password;
		HOST = host;
		PORT = port;
		PROTOCOL = protocol;
		PROPERTIES = new Properties();
		if(auth) {
			PROPERTIES.setProperty("mail.smtp.auth", "true");
		}
		if(useSsl) {
			PROPERTIES.setProperty("mail.smtp.port", Integer.toString(port));//设置端口
			PROPERTIES.setProperty("mail.smtp.socketFactory.port", Integer.toString(port));//设置ssl端口
			PROPERTIES.setProperty("mail.smtp.socketFactory.fallback", "false");
			PROPERTIES.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
	}
}