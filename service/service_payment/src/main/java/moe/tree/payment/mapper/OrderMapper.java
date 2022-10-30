package moe.tree.payment.mapper;

import moe.tree.payment.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author natuki
 * @since 2022-10-28
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
