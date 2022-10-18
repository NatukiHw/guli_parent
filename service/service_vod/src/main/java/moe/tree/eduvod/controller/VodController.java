package moe.tree.eduvod.controller;

import moe.tree.commontuils.R;
import moe.tree.eduvod.service.VodService;
import moe.tree.servicebase.exception.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/videos")
public class VodController {

	@Autowired
	private VodService vodService;

	@PostMapping("/upload")
	public R uploadVideo(MultipartFile file) {
		String videoId = vodService.uploadVideo(file);
		return R.ok().data("videoId", videoId);
	}

	@DeleteMapping("/{videoId}")
	public R deleteVideo(@PathVariable String videoId) {
		boolean res = vodService.deleteVideo(videoId);
		if(!res) {
			throw new GuliException(20001, "视频删除失败");
		}
		return R.ok();
	}

	@DeleteMapping("/")
	public R deleteBatchVideos(@RequestParam("videoIdList") List<String> videoIdList) {
		boolean res = vodService.deleteBatchVideos(videoIdList);
		if(!res) {
			throw new GuliException(20001, "视频删除失败");
		}
		return R.ok();
	}
}
