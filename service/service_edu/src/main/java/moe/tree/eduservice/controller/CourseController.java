package moe.tree.eduservice.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.vo.CourseVo;
import moe.tree.eduservice.service.ChapterService;
import moe.tree.eduservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
