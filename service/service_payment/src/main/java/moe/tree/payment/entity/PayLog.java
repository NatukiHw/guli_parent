package moe.tree.payment.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author natuki
 * @since 2022-10-28
 */
@Getter
@Setter
@TableName("edu_pay_log")
@ApiModel(value = "PayLog对象", description = "")
public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("订单号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("支付完成时间")
    @TableField("pay_time")
    private LocalDateTime payTime;

    @ApiModelProperty("支付金额（分）")
    @TableField("total_fee")
    private BigDecimal totalFee;

    @ApiModelProperty("交易流水号")
    @TableField("transaction_id")
    private String transactionId;

    @ApiModelProperty("交易状态")
    @TableField("trade_state")
    private String tradeState;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    @TableField("pay_type")
    private Byte payType;

    @ApiModelProperty("其他属性")
    @TableField("attr")
    private String attr;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Byte isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
