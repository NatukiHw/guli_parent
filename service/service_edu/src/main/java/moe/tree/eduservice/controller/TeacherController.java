package moe.tree.eduservice.controller;

import java.util.List;

import moe.tree.eduservice.entity.Teacher;
import moe.tree.eduservice.service.TeacherService;
import moe.tree.eduservice.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-09-27
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class TeacherController {

	@Autowired
	private TeacherServiceImpl teacherService;

	@GetMapping("/findAll")
	public List<Teacher> findAllTeacher() {
		List<Teacher> list = teacherService.list();
		return list;
	}

	@DeleteMapping("{id}")
	public boolean removeTeacher(@PathVariable String id) {
		boolean flag = teacherService.removeById(id);
		return flag;
	}
}
