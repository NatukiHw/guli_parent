package moe.tree.educms.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Video;
import moe.tree.eduservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educms/front/videos")
public class VideoFrontController {

	@Autowired
	public VideoService videoService;

	@GetMapping("/{videoId}")
	public R getVideo(@PathVariable String videoId) {
		Video video = videoService.getById(videoId);
		return R.ok().data("video", video);
	}
}
