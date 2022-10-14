package moe.tree.eduservice.service;

import moe.tree.eduservice.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import moe.tree.eduservice.entity.vo.CoursePublishVo;
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

	CoursePublishVo getCoursePublishVo(String courseId);
}
