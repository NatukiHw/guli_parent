package moe.tree.eduservice.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Teacher;
import moe.tree.eduservice.entity.vo.TeacherQuery;
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
@RequestMapping("/eduservice/teachers")
public class TeacherController {

	@Autowired
	private TeacherServiceImpl teacherService;

//	@GetMapping("/")
//	public R findAllTeacher() {
//		List<Teacher> list = teacherService.list();
//		return R.ok().data("items", list);
//	}

	@DeleteMapping("/{id}")
	public R removeTeacher(@PathVariable String id) {
		boolean flag = teacherService.removeById(id);
		if(flag) {
			return R.ok();
		} else {
			return R.error();
		}
	}

//	@GetMapping("/")
//	public R pageListTeacher(long page, long limit) {
//		Page<Teacher> pageTeacher = new Page<>(page, limit);
//		teacherService.page(pageTeacher, null);
//
//		long total = pageTeacher.getTotal();
//		List<Teacher> records = pageTeacher.getRecords();
//
//		return R.ok().data("total", total).data("rows", records);
//	}

	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public R pageTeacherCondition(long page, long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
		Page<Teacher> pageTeacher = new Page<>(page, limit);

		QueryWrapper<Teacher> wrapper = null;
		if(teacherQuery != null) {
			wrapper =  new QueryWrapper<>();
			String name = teacherQuery.getName();
			Integer level = teacherQuery.getLevel();
			String begin = teacherQuery.getBegin();
			String end = teacherQuery.getEnd();
			if(!StringUtils.isEmpty(name)) {
				wrapper.like("name", name);
			}
			if(level != null) {
				wrapper.eq("level", level);

			}
			if(!StringUtils.isEmpty(begin)) {
				wrapper.ge("gmt_create", begin);
			}
			if(!StringUtils.isEmpty(end)) {
				wrapper.le("gmt_create", end);
			}
		}

		teacherService.page(pageTeacher, wrapper);

		long total = pageTeacher.getTotal();
		List<Teacher> records = pageTeacher.getRecords();

		return R.ok().data("total", total).data("rows", records);
	}
}
