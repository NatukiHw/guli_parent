package moe.tree.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import moe.tree.commontuils.CoursePublishVo;
import moe.tree.commontuils.MemberProfile;
import moe.tree.commontuils.R;
import moe.tree.payment.client.CourseClient;
import moe.tree.payment.client.MemberClient;
import moe.tree.payment.entity.AliBean;
import moe.tree.payment.entity.Order;
import moe.tree.payment.mapper.OrderMapper;
import moe.tree.payment.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.tree.payment.util.AlipayConstantPropertiesUtil;
import moe.tree.payment.util.OrderNoUtil;
import moe.tree.servicebase.exception.GuliException;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-10-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	@Autowired
	private MemberClient memberClient;

	@Autowired
	private CourseClient courseClient;

	@Override
	public String createOrder(String memberId, String courseId) {
		R res = memberClient.getMemberProfile(memberId);
		Map<String, Object> map = (Map<String, Object>) res.getData().get("profile");
		if(map == null) {
			throw new GuliException(20001, "获取用户信息失败");
		}
		JSONObject jsonObject =  new JSONObject(map);
		MemberProfile memberProfile = jsonObject.toJavaObject(MemberProfile.class);

		res = courseClient.getCoursePublishVo(courseId);
		map =  (Map<String, Object>) res.getData().get("course");
		if(map == null) {
			throw new GuliException(20001, "获取课程信息失败");
		}
		jsonObject =  new JSONObject(map);
		CoursePublishVo coursePublishVo = jsonObject.toJavaObject(CoursePublishVo.class);

		String orderNo = OrderNoUtil.generateOrderNo();
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setCourseId(courseId);
		order.setCourseTitle(coursePublishVo.getTitle());
		order.setCourseCover(coursePublishVo.getCover());
		order.setTeacherName(coursePublishVo.getTeacherName());
		order.setMemberId(memberId);
		order.setNickname(memberProfile.getNickname());
		order.setMobile(memberProfile.getMobile());
		order.setTotalFee(new BigDecimal(coursePublishVo.getPrice()));
		order.setPayType((byte) 2);
		order.setStatus((byte) 0);
		int insertRes = baseMapper.insert(order);
		if(insertRes <= 0) {
			return null;
		}
		return orderNo;
	}



	@Override
	public String payOrderWithAlipay(String orderNo) {
		Order order = getOrderByOrderNo(orderNo);
		if(order == null) {
			throw new GuliException(20001, "订单不存在");
		}

		AliBean aliBean = new AliBean();
		aliBean.setOut_trade_no(order.getOrderNo());
		aliBean.setSubject(order.getCourseTitle());
		aliBean.setTotal_amount(order.getTotalFee().toString());
		aliBean.setBody(order.toString());

		AlipayClient alipayClient = getAlipayClientWithAlipayPublicKey();

		String returnUrl = AlipayConstantPropertiesUtil.RETURN_URL;
		String notifyUrl = AlipayConstantPropertiesUtil.NOTIFY_URL;

		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 页面跳转同步通知页面路径
		alipayRequest.setReturnUrl(returnUrl);
		// 服务器异步通知页面路径
		alipayRequest.setNotifyUrl(notifyUrl);
		// 封装参数
		ObjectMapper mapper = new ObjectMapper();
		String bizContent = null;
		try {
			bizContent = mapper.writeValueAsString(aliBean);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new GuliException(20001, "生成支付宝订单失败");
		}
		alipayRequest.setBizContent(bizContent);
		// 3、请求支付宝进行付款，并获取支付结果
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new GuliException(20001, "生成支付宝订单失败");
		}
		// 返回付款信息
		return result;
	}

	public String getOrderStatusWithAlipay(String orderNo) {
		AlipayClient alipayClient = getAlipayClientWithAlipayPublicKey();
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		Map<String, Object> map = new HashedMap<>();
		map.put("out_trade_no", orderNo);
		ObjectMapper objectMapper = new ObjectMapper();
		String bizContent = null;
		try {
			bizContent = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new GuliException(20001, "查询订单状态失败");
		}
		request.setBizContent(bizContent);
		try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			return response.getTradeStatus();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new GuliException(20001, "查询订单状态失败");
		}
	}

	@Override
	public Order getOrderByOrderNo(String orderNo) {
		QueryWrapper<Order> wrapper = new QueryWrapper<>();
		wrapper.eq("order_no", orderNo);
		Order order = baseMapper.selectOne(wrapper);
		if(order == null) {
			throw new GuliException(20001, "订单不存在");
		}
		if(order.getStatus() == AliBean.WAIT_BUYER_PAY) {
			String status = getOrderStatusWithAlipay(orderNo);
			if(status == null) {
				return order;
			}
			byte statusVal = AliBean.WAIT_BUYER_PAY;
			switch (status) {
				case "TRADE_CLOSED":
					statusVal = AliBean.TRADE_CLOSED;
					break;
				case "TRADE_SUCCESS":
					statusVal = AliBean.TRADE_SUCCESS;
					break;
				case "TRADE_FINISHED":
					statusVal = AliBean.TRADE_FINISHED;
					break;
			}
			Order newOrder = new Order();
			newOrder.setId(order.getId());
			newOrder.setStatus(statusVal);
			int res = baseMapper.updateById(newOrder);
			if(res > 0) {
				order.setStatus(statusVal);
			}
		}
		return order;
	}

	private AlipayClient getAlipayClientWithAppPublicKey() {
		String gatewayUrl = AlipayConstantPropertiesUtil.GATEWAY_URL;
		String appId = AlipayConstantPropertiesUtil.APP_ID;
		String privateKey = AlipayConstantPropertiesUtil.PRIVATE_KEY;
		String format = "json";
		String charset = AlipayConstantPropertiesUtil.CHARSET;
		String publicKey = AlipayConstantPropertiesUtil.APP_PUBLIC_KEY;
		String signType = AlipayConstantPropertiesUtil.SIGN_TYPE;

		AlipayClient alipayClient =  new DefaultAlipayClient(gatewayUrl , appId, privateKey, format, charset, publicKey, signType);
		return alipayClient;
	}

	private AlipayClient getAlipayClientWithAlipayPublicKey() {
		String gatewayUrl = AlipayConstantPropertiesUtil.GATEWAY_URL;
		String appId = AlipayConstantPropertiesUtil.APP_ID;
		String privateKey = AlipayConstantPropertiesUtil.PRIVATE_KEY;
		String format = "json";
		String charset = AlipayConstantPropertiesUtil.CHARSET;
		String publicKey = AlipayConstantPropertiesUtil.ALIPAY_PUBLIC_KEY;
		String signType = AlipayConstantPropertiesUtil.SIGN_TYPE;

		AlipayClient alipayClient =  new DefaultAlipayClient(gatewayUrl , appId, privateKey, format, charset, publicKey, signType);
		return alipayClient;
	}
}
