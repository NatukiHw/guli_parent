package moe.tree.payment.entity;

import lombok.Data;

@Data
public class AliBean {

	public static final byte WAIT_BUYER_PAY = 0;

	public static final byte TRADE_CLOSED = 3;

	public static final byte TRADE_SUCCESS = 1;

	public static final byte TRADE_FINISHED = 2;

	/**
	 * 商户订单号，必填
	 */
	private String out_trade_no;
	/**
	 * 订单名称，必填
	 */
	private String subject;
	/**
	 * 付款金额，必填
	 * 根据支付宝接口协议，必须使用下划线
	 */
	private String total_amount;
	/**
	 * 商品描述，可空
	 */
	private String body;
	/**
	 * 超时时间参数
	 */
	private String timeout_express = "1h";
	/**
	 * 产品编号
	 */
	private String product_code = "FAST_INSTANT_TRADE_PAY";
}
