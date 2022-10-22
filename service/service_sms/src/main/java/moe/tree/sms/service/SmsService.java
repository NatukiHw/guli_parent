package moe.tree.sms.service;

import moe.tree.sms.entity.Code;

public interface SmsService {
	public void sendSmsCode(String telephone, Code code);
}
