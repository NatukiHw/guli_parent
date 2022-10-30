package moe.tree.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Course;
import moe.tree.commontuils.CoursePublishVo;
import moe.tree.eduservice.entity.vo.CourseQuery;
import moe.tree.eduservice.entity.vo.CourseVo;
import moe.tree.eduservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/eduservice/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/")
	public R addCourse(@RequestBody CourseVo courseVo) {
		String courseId = courseService.saveCourse(courseVo);
		return R.ok().data("courseId", courseId);
	}

	@GetMapping("/{courseId}")
	public R getCourse(@PathVariable String courseId) {
		CourseVo courseVo = courseService.getCourse(courseId);
		return R.ok().data("course", courseVo);
	}

	@PutMapping("/")
	public R updateCourse(@RequestBody CourseVo courseVo) {
		courseService.updateCourse(courseVo);
		return R.ok();
	}

	@GetMapping("/{courseId}/publisher")
	public R getCoursePublishVo(@PathVariable String courseId) {
		CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(courseId);
		return R.ok().data("course", coursePublishVo);
	}

	@PostMapping("/{courseId}/publisher")
	public R publishCourse(@PathVariable String courseId) {
		courseService.publishCourse(courseId);
		return R.ok();
	}

	@RequestMapping(value = "/view", method = {RequestMethod.GET, RequestMethod.POST})
	public R pageCourseCondition(long page, long limit, @RequestBody(required = false) CourseQuery courseQuery) {
		Page<Course> pageCourse = new Page<>(page, limit);

		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("gmt_create");

		if (courseQuery != null) {
			String title = courseQuery.getTitle();
			String teacherId = courseQuery.getTeacherId();
			String parentSubjectId = courseQuery.getParentSubjectId();
			String childSubjectId = courseQuery.getChildSubjectId();
			String begin = courseQuery.getBegin();
			String end = courseQuery.getEnd();
			if (!StringUtils.isEmpty(title)) {
				wrapper.like("title", title);
			}
			if (!StringUtils.isEmpty(teacherId)) {
				wrapper.eq("teacher_id", teacherId);
			}
			if (!StringUtils.isEmpty(parentSubjectId)) {
				wrapper.eq("subject_id", childSubjectId);
			}
			if (!StringUtils.isEmpty(childSubjectId)) {
				wrapper.eq("subject_parent_id", parentSubjectId);
			}
			if (!StringUtils.isEmpty(begin)) {
				wrapper.ge("gmt_create", begin);
			}
			if (!StringUtils.isEmpty(end)) {
				wrapper.le("gmt_create", end);
			}
		}

		courseService.page(pageCourse, wrapper);

		long total = pageCourse.getTotal();
		List<Course> records = pageCourse.getRecords();

		return R.ok().data("total", total).data("rows", records);
	}

	@DeleteMapping("/{courseId}")
	public R deleteCourse(@PathVariable String courseId) {
		boolean res = courseService.removeCourse(courseId);
		if(res) {
			return R.ok();
		} else {
			return R.error();
		}
	}
}
