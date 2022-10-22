package moe.tree.sms.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import moe.tree.commontuils.R;
import moe.tree.sms.entity.Code;
import moe.tree.sms.service.SmsService;
import moe.tree.sms.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edusms")
public class SmsController {

	@Autowired
	private SmsService smsService;

	@Autowired
	private RedisTemplate redisTemplate;

	@GetMapping("/send/{telephone}")
	public R sendSmsCode(@PathVariable String telephone) {
		String codeVal = (String) redisTemplate.opsForValue().get("guliSms::"+telephone);
		if(!StringUtils.isEmpty(codeVal)) {
			return R.error().message("重复请求验证码");
		}
		codeVal = RandomUtil.getSixBitRandom();
		Code code = new Code(codeVal);
		smsService.sendSmsCode(telephone, code);
		redisTemplate.opsForValue().set("guliSms::"+telephone, codeVal, 5, TimeUnit.MINUTES);
		return R.ok();
	}
}
