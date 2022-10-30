package moe.tree.educms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.educms.entity.CourseDetail;
import moe.tree.educms.entity.vo.CourseFrontVo;
import moe.tree.eduservice.entity.Course;

public interface CourseFrontService {

	public void getList(Page<Course> coursePage, CourseFrontVo courseFrontVo);

	public CourseDetail getDetail(String courseId);
}
