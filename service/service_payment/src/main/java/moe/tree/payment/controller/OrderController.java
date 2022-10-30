package moe.tree.payment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.tree.commontuils.R;
import moe.tree.payment.entity.Order;
import moe.tree.payment.service.OrderService;
import moe.tree.servicebase.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-10-28
 */
@RestController
@RequestMapping("/payment/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping ("/{courseId}")
	public R createOrder(HttpServletRequest request, @PathVariable String courseId) {
		String memberId = JWTUtil.getMemberId(request);
		String orderId = orderService.createOrder(memberId, courseId);
		return R.ok().data("orderNo", orderId);
	}

	@GetMapping("/{orderNo}")
	public R getOrder(@PathVariable String orderNo) {
		Order order = orderService.getOrderByOrderNo(orderNo);
		return R.ok().data("order", order);
	}

	@GetMapping("/{orderNo}/alipay")
	@ResponseBody
	public String payOrderWithAlipay(@PathVariable String orderNo, HttpServletResponse response) {
		String result = orderService.payOrderWithAlipay(orderNo);
		response.setContentType("text/html");
		return result;
	}


}
