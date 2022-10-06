package moe.tree.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.tree.commontuils.R;
import moe.tree.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(description="阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/eduoss")
public class FileController {

	@Autowired
	private FileService fileService;
	/**
	 * 文件上传
	 *
	 * @param file
	 */
	@ApiOperation(value = "头像上传")
	@PostMapping("/avatar/upload")
	public R upload(@ApiParam(name = "file", value = "文件", required = true) @RequestParam("file") MultipartFile file) {
		String uploadUrl = fileService.uploadAvatar(file);
		return R.ok().message("上传成功").data("url", uploadUrl);
	}
}
