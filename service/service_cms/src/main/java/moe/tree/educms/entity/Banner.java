package moe.tree.educms.entity;

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
 * @since 2022-10-20
 */
@Getter
@Setter
@TableName("edu_banner")
@ApiModel(value = "Banner对象", description = "")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("图片地址")
    @TableField("image_url")
    private String imageUrl;

    @ApiModelProperty("链接地址")
    @TableField("link_url")
    private String linkUrl;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Byte isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
