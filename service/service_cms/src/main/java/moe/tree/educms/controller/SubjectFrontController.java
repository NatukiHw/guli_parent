package moe.tree.educms.controller;

import moe.tree.commontuils.R;
import moe.tree.eduservice.entity.Subject;
import moe.tree.eduservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/educms/front/subjects")
public class SubjectFrontController {

	@Autowired
	SubjectService subjectService;

	@GetMapping("/")
	public R getSubjectList() {
		List<Subject> list = subjectService.list();
		return R.ok().data("subjects", list);
	}
}
