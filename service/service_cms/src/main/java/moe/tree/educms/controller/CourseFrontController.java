package moe.tree.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.commontuils.R;
import moe.tree.educms.entity.ChapterFront;
import moe.tree.educms.entity.CourseDetail;
import moe.tree.educms.entity.vo.CourseFrontVo;
import moe.tree.educms.service.ChapterFrontService;
import moe.tree.educms.service.CourseFrontService;
import moe.tree.eduservice.entity.Course;
import moe.tree.eduservice.entity.vo.ChapterVo;
import moe.tree.eduservice.service.ChapterService;
import moe.tree.eduservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educms/front/courses")
public class CourseFrontController {

	@Autowired
	CourseFrontService courseFrontService;

	@Autowired
	ChapterFrontService chapterFrontService;

	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public R getPageCondition(long page, long limit, @RequestBody(required = false) CourseFrontVo courseFrontVo) {
		Page<Course> coursePage = new Page<>(page, limit);
		courseFrontService.getList(coursePage, courseFrontVo);
		long total = coursePage.getTotal();
		List<Course> rows = coursePage.getRecords();
		return R.ok().data("total", total).data("rows", rows);
	}

	@GetMapping("/{courseId}")
	public R getCourseDetail(@PathVariable String courseId) {
		CourseDetail courseDetail = courseFrontService.getDetail(courseId);
		return R.ok().data("course", courseDetail);
	}

	@GetMapping("/{courseId}/chapters")
	public R getChapterList(@PathVariable String courseId) {
		List<ChapterFront> chapterList = chapterFrontService.getChapterListByCourseId(courseId);
		return R.ok().data("chapters", chapterList);
	}
}
