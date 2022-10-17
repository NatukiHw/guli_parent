package moe.tree.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程名称,模糊查询")
	private String title;

	@ApiModelProperty(value = "讲师ID")
	private String teacherId;

	@ApiModelProperty(value = "父分类ID")
	private String parentSubjectId;

	@ApiModelProperty(value = "二级分类ID")
	private String childSubjectId;

	@ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
	private String begin;

	@ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
	private String end;
}