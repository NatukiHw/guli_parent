package moe.tree.educms.mapper;

import moe.tree.educms.entity.CourseDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseFrontMapper {
	public CourseDetail getFrontCourseDetail(String courseId);
}
