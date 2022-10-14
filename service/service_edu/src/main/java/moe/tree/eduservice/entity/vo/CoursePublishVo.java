package moe.tree.eduservice.entity.vo;

import lombok.Data;

@Data
public class CoursePublishVo {

	private String id;
	private String title;
	private String cover;
	private Integer lessonNum;
	private String parentSubject;
	private String childrenSubject;
	private String teacherName;
	private String price;
}