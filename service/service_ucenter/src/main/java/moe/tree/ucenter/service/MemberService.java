package moe.tree.ucenter.service;

import moe.tree.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import moe.tree.commontuils.MemberProfile;
import moe.tree.ucenter.entity.vo.MemberLoginVo;
import moe.tree.ucenter.entity.vo.MemberRegisterVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author natuki
 * @since 2022-10-22
 */
public interface MemberService extends IService<Member> {

	String login(MemberLoginVo memberLoginVo);

	void register(MemberRegisterVo memberRegisterVo);

	MemberProfile getProfile(String id);

	Integer countDailyRegister(String day);
}
