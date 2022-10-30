package moe.tree.commontuils;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberProfile {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("会员id")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	@ApiModelProperty("手机号")
	@TableField("mobile")
	private String mobile;

	@ApiModelProperty("昵称")
	@TableField("nickname")
	private String nickname;

	@ApiModelProperty("性别 1 女，2 男")
	@TableField("sex")
	private Byte sex;

	@ApiModelProperty("出生日期")
	@TableField("birthday")
	private LocalDateTime birthday;

	@ApiModelProperty("用户头像")
	@TableField("avatar")
	private String avatar;

	@ApiModelProperty("用户签名")
	@TableField("sign")
	private String sign;

	@ApiModelProperty("是否禁用 1（true）已禁用，  0（false）未禁用")
	@TableField("is_disabled")
	private Byte isDisabled;

	@ApiModelProperty("创建时间")
	@TableField(value = "gmt_create", fill = FieldFill.INSERT)
	private LocalDateTime gmtCreate;
}
