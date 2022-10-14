package moe.tree.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@Getter
@Setter
@TableName("edu_chapter")
@ApiModel(value = "Chapter对象", description = "课程")
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("章节ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("课程ID")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty("章节名称")
    @TableField("title")
    private String title;

    @ApiModelProperty("显示排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime gmtModified;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Byte isDeleted;
}
