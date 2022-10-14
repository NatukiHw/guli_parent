package moe.tree.eduservice.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Video;
import moe.tree.eduservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/eduservice/videos")
public class VideoController {

	@Autowired
	public VideoService videoService;

	@GetMapping("/{videoId}")
	public R getVideo(@PathVariable String videoId) {
		Video video = videoService.getById(videoId);
		return R.ok().data("video", video);
	}

	@PostMapping("/")
	public R addVideo(@RequestBody Video video) {
		videoService.save(video);
		return R.ok();
	}

	@PutMapping("/")
	public R updateVideo(@RequestBody Video video) {
		videoService.updateById(video);
		return R.ok();
	}

	@DeleteMapping("/{videoId}")
	public R deleteVideo(@PathVariable String videoId) {
		videoService.removeById(videoId);
		return R.ok();
	}
}
