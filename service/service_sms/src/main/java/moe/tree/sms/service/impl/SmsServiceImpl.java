package moe.tree.sms.service.impl;

import moe.tree.sms.entity.Code;
import moe.tree.sms.service.SmsService;
import moe.tree.sms.util.ConstantPropertiesUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

	@Override
	public void sendSmsCode(String telephone, Code code) {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(ConstantPropertiesUtil.HOST);
		javaMailSender.setPort(ConstantPropertiesUtil.PORT);
		javaMailSender.setUsername(ConstantPropertiesUtil.USERNAME);
		javaMailSender.setPassword(ConstantPropertiesUtil.PASSWORD);
		javaMailSender.setProtocol(ConstantPropertiesUtil.PROTOCOL);
		javaMailSender.setJavaMailProperties(ConstantPropertiesUtil.PROPERTIES);

		SimpleMailMessage message = new SimpleMailMessage();
		//邮件发送人
		message.setFrom(ConstantPropertiesUtil.USERNAME);
		//邮件接收人
		message.setTo(telephone);
		//邮件主题
		message.setSubject("谷粒学院验证码");
		//邮件内容
		message.setText("[谷粒学院]这是您的验证码：" + code.getCode() + "，验证码五分钟内有效");
		//发送邮件
		javaMailSender.send(message);
	}
}
