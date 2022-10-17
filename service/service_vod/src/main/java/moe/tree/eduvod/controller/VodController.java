package moe.tree.eduvod.controller;

import moe.tree.commontuils.R;
import moe.tree.eduvod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
