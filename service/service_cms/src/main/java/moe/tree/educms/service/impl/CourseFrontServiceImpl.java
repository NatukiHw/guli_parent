package moe.tree.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.educms.entity.CourseDetail;
import moe.tree.educms.entity.vo.CourseFrontVo;
import moe.tree.educms.mapper.CourseFrontMapper;
import moe.tree.educms.service.CourseFrontService;
import moe.tree.eduservice.entity.Course;
import moe.tree.eduservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseFrontServiceImpl implements CourseFrontService {

	@Autowired
	CourseService courseService;

	@Autowired
	CourseFrontMapper courseFrontMapper;

	@Override
	public void getList(Page<Course> coursePage, CourseFrontVo courseFrontVo) {
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("status", Course.COURSE_NORMAL);

		if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
			wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
		}
		if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
			wrapper.eq("subject_id", courseFrontVo.getSubjectId());
		}
		if(courseFrontVo.getBuyCountSort() != null && courseFrontVo.getBuyCountSort()) {
			wrapper.orderByDesc("buy_count");
		}
		if(courseFrontVo.getGmtCreateSort() != null && courseFrontVo.getGmtCreateSort()) {
			wrapper.orderByDesc("gmt_create");
		}
		if(courseFrontVo.getPriceSort() != null && courseFrontVo.getPriceSort()) {
			wrapper.orderByDesc("price");
		}
		courseService.page(coursePage, wrapper);
	}

	@Override
	public CourseDetail getDetail(String courseId) {
		return courseFrontMapper.getFrontCourseDetail(courseId);
	}
}
