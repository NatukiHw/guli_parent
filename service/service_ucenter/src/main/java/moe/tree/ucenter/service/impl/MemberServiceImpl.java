package moe.tree.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import moe.tree.servicebase.util.JWTUtil;
import moe.tree.servicebase.exception.GuliException;
import moe.tree.ucenter.entity.Member;
import moe.tree.ucenter.entity.vo.MemberLoginVo;
import moe.tree.ucenter.entity.vo.MemberRegisterVo;
import moe.tree.ucenter.mapper.MemberMapper;
import moe.tree.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-10-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

	@Value("${ucenter.defaultAvatar}")
	public static String DEFAULT_AVATAR;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public String login(MemberLoginVo memberLoginVo) {
		String username = memberLoginVo.getUsername();
		String password = memberLoginVo.getPassword();

		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new GuliException(20001, "用户名或密码不能为空");
		}

		QueryWrapper<Member> wrapper = new QueryWrapper<>();
		wrapper.eq("mobile", memberLoginVo.getUsername());
		Member member = baseMapper.selectOne(wrapper);
		if(member == null) {
			throw new GuliException(20001, "用户名或密码错误");
		}
		if(!member.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			throw new GuliException(20001, "用户名或密码错误");
		}
		if(member.getIsDisabled() == 1) {
			throw new GuliException(20001, "用户被禁用");
		}
		//Get token
		Map<String, Object> payload = new HashedMap<>();
		payload.put("username", username);
		payload.put("nickname", member.getNickname());
		String token = JWTUtil.getToken(payload);
		return token;
	}

	@Override
	public void register(MemberRegisterVo memberRegisterVo) {
		String code = memberRegisterVo.getCode();
		String mobile = memberRegisterVo.getMobile();
		String nickname = memberRegisterVo.getNickname();
		String password = memberRegisterVo.getPassword();

		if(StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
			throw new GuliException(20001, "请填写完整的注册信息");
		}

		String smsCode = (String)redisTemplate.opsForValue().get("guliSms::" + mobile);
		if(!code.equals(smsCode)) {
			throw new GuliException(20001, "验证码错误");
		}

		QueryWrapper<Member> wrapper = new QueryWrapper<>();
		wrapper.eq("mobile", mobile);
		long count = baseMapper.selectCount(wrapper);
		if(count > 0) {
			throw new GuliException(20001, "用户名已被占用");
		}

		Member member = new Member();
		member.setMobile(mobile);
		member.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		member.setNickname(nickname);
		member.setAvatar(DEFAULT_AVATAR);
		member.setIsDisabled((byte) 0);
		baseMapper.insert(member);
	}
}
