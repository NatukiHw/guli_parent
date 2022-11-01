package moe.tree.ucenter.mapper;

import moe.tree.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author natuki
 * @since 2022-10-22
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
	Integer countDailyRegister(String date);
}
