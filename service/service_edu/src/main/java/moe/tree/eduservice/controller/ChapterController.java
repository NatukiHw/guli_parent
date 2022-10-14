package moe.tree.eduservice.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Chapter;
import moe.tree.eduservice.entity.vo.ChapterVo;
import moe.tree.eduservice.service.ChapterService;
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
@RequestMapping("/eduservice/")
public class ChapterController {

	@Autowired
	private ChapterService chapterService;


	@GetMapping("/courses/{courseId}/chapters")
	public R getChapterList(@PathVariable String courseId) {
		List<ChapterVo> list = chapterService.getChapterListByCourseId(courseId);
		return R.ok().data("chapters", list);
	}

	@GetMapping("/chapters/{chapterId}")
	public R getChapter(@PathVariable String chapterId) {
		Chapter chapter = chapterService.getById(chapterId);
		return R.ok().data("chapter", chapter);
	}

	@PostMapping("/chapters/")
	public R addChapter(@RequestBody Chapter chapter) {
		boolean res = chapterService.save(chapter);
		if(res) {
			return R.ok();
		} else {
			return R.error();
		}
	}

	@PutMapping("/chapters/")
	public R updateChapter(@RequestBody Chapter chapter) {
		boolean res = chapterService.updateById(chapter);
		if(res) {
			return R.ok();
		} else {
			return R.error();
		}
	}

	@DeleteMapping("/chapters/{chapterId}")
	public R deleteChapter(@PathVariable String chapterId) {
		boolean res = chapterService.deleteChapter(chapterId);
		if(res) {
			return R.ok();
		} else {
			return R.error();
		}
	}
}
