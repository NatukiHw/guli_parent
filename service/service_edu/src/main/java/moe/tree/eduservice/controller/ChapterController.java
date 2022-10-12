package moe.tree.eduservice.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.vo.ChapterVo;
import moe.tree.eduservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/eduservice/")
public class ChapterController {

	@Autowired
	private ChapterService chapterService;


	@GetMapping("/courses/{courseId}/chapters")
	public R getChapterList(@PathVariable String courseId) {
		List<ChapterVo> list = chapterService.getChapterListByCourseId(courseId);
		return R.ok().data("chapters", list);
	}
}
