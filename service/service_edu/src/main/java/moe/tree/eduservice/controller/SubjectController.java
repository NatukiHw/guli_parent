package moe.tree.eduservice.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Subject;
import moe.tree.eduservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/eduservice/subjects")
public class SubjectController {

	@Autowired
	SubjectService subjectService;

	@PostMapping("/upload")
	public R uploadSubjectExcel(MultipartFile file) {
		subjectService.processExcel(file);
		return R.ok();
	}

	@GetMapping("/")
	public R getSubjectList() {
		List<Subject> list = subjectService.list();
		return R.ok().data("subjects", list);
	}
}
