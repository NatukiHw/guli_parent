package moe.tree.eduservice.service;

import moe.tree.eduservice.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import moe.tree.commontuils.CoursePublishVo;
import moe.tree.eduservice.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
public interface CourseService extends IService<Course> {

	public String saveCourse(CourseVo courseVo);

	public CourseVo getCourse(String courseId);

	public void updateCourse(CourseVo courseVo);

	public CoursePublishVo getCoursePublishVo(String courseId);

	public boolean publishCourse(String courseId);

	public boolean removeCourse(String courseId);
}
