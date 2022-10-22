package moe.tree.educms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.tree.commontuils.R;
import moe.tree.educms.entity.Banner;
import moe.tree.educms.service.BannerService;
import moe.tree.eduservice.entity.Course;
import moe.tree.eduservice.entity.Teacher;
import moe.tree.eduservice.service.CourseService;
import moe.tree.eduservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/educms/front/index")
public class IndexFrontController {
	@Autowired
	private BannerService bannerService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseService courseService;

	@Cacheable(value="guliCms", key="'frontBannerList'")
	@GetMapping("/banners")
	public R getFrontBannerList() {
		QueryWrapper<Banner> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("sort", "gmt_create");
		List<Banner> list = bannerService.list(wrapper);
		return R.ok().data("rows", list);
	}

	@Cacheable(value="guliCms", key="'frontRecommendContent'")
	@GetMapping("/recommend")
	public R getFrontHomePageData() {
		R res = R.ok();
		QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
		courseWrapper.eq("status", Course.COURSE_NORMAL);
		courseWrapper.orderByDesc("buy_count", "gmt_create");
		courseWrapper.last("limit 8");
		List<Course> courseList = courseService.list(courseWrapper);
		res.data("courseList", courseList);

		QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
		teacherWrapper.orderByDesc("sort", "gmt_create");
		teacherWrapper.last("limit 4");
		List<Teacher> teacherList = teacherService.list(teacherWrapper);
		res.data("teacherList", teacherList);

		return res;
	}
}
