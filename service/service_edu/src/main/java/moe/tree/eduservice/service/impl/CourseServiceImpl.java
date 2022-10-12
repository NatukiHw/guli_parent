package moe.tree.eduservice.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import moe.tree.eduservice.entity.Course;
import moe.tree.eduservice.entity.CourseDescription;
import moe.tree.eduservice.entity.vo.CourseVo;
import moe.tree.eduservice.mapper.CourseMapper;
import moe.tree.eduservice.service.CourseDescriptionService;
import moe.tree.eduservice.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.tree.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

	@Autowired
	CourseDescriptionService courseDescriptionService;

	@Override
	public String saveCourse(CourseVo courseVo) {
		Course course = new Course();
		BeanUtils.copyProperties(courseVo, course);
		int insert = baseMapper.insert(course);
		if(insert <= 0) {
			throw new GuliException(20001, "保存课程信息失败");
		}
		String courseId = course.getId();
		CourseDescription courseDescription = new CourseDescription();
		courseDescription.setId(courseId);
		courseDescription.setDescription(courseVo.getDescription());
		boolean result = courseDescriptionService.save(courseDescription);
		if(!result) {
			throw new GuliException(20001, "保存课程描述失败");
		}
		return courseId;
	}

	@Override
	public CourseVo getCourse(String courseId) {
		Course course = baseMapper.selectById(courseId);
		CourseVo courseVo = new CourseVo();
		BeanUtils.copyProperties(course, courseVo);

		CourseDescription courseDescription = courseDescriptionService.getById(courseId);
		courseVo.setDescription(courseDescription.getDescription());

		return courseVo;
	}

	@Override
	public void updateCourse(CourseVo courseVo) {
		Course course = new Course();
		BeanUtils.copyProperties(courseVo, course);
		int updateRes = baseMapper.updateById(course);
		if(updateRes <= 0) {
			throw new GuliException(20001, "课程信息修改失败");
		}

		CourseDescription courseDescription = new CourseDescription();
		courseDescription.setId(course.getId());
		courseDescription.setDescription(courseVo.getDescription());
		boolean descUpdateRes = courseDescriptionService.updateById(courseDescription);
		if(!descUpdateRes) {
			throw new GuliException(20001, "课程信息修改失败");
		}
	}
}
