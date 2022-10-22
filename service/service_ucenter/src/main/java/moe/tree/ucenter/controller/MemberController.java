package moe.tree.ucenter.controller;

import moe.tree.commontuils.R;
import moe.tree.ucenter.entity.vo.MemberLoginVo;
import moe.tree.ucenter.entity.vo.MemberRegisterVo;
import moe.tree.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-10-22
 */
@RestController
@RequestMapping("/ucenter/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public R loginMember(@RequestBody MemberLoginVo memberLoginVo) {
		String token = memberService.login(memberLoginVo);
		return R.ok().data("token", token);
	}

	@PostMapping("/register")
	public R RegisterMember(@RequestBody MemberRegisterVo memberRegisterVo) {
		memberService.register(memberRegisterVo);
		return R.ok();
	}
}
