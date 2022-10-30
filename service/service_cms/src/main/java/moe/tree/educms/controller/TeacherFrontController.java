package moe.tree.educms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Course;
import moe.tree.eduservice.entity.Teacher;
import moe.tree.eduservice.service.CourseService;
import moe.tree.eduservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/educms/front/teachers")
public class TeacherFrontController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/")
	public R getTeacherList(long page, long limit) {
		Page<Teacher> pageTeacher = new Page<>(page, limit);

		QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("sort", "gmt_create");

		teacherService.page(pageTeacher, wrapper);

		long total = pageTeacher.getTotal();
		List<Teacher> records = pageTeacher.getRecords();

		return R.ok().data("total", total).data("rows", records);
	}

	@GetMapping("/{id}")
	public R getTeacherDetail(@PathVariable String id) {
		Teacher teacher = teacherService.getById(id);

		QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
		courseWrapper.eq("teacher_id", id);
		List<Course> courseList = courseService.list(courseWrapper);

		return R.ok().data("teacher", teacher).data("courseList", courseList);
	}
}
