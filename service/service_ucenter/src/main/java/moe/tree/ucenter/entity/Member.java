package moe.tree.ucenter.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import moe.tree.commontuils.MemberProfile;

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
public class Member extends MemberProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("微信openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Byte isDeleted;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
