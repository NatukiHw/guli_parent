package moe.tree.payment.service;

import moe.tree.payment.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author natuki
 * @since 2022-10-28
 */
public interface OrderService extends IService<Order> {
	String createOrder(String memberId, String courseId);

	String payOrderWithAlipay(String orderNo);

	Order getOrderByOrderNo(String orderNo);
}
