package moe.tree.ucenter.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-10-22
 */
@Getter
@Setter
@TableName("edu_member")
@ApiModel(value = "Member对象", description = "")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会员id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("微信openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

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
