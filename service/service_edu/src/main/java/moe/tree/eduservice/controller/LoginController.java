package moe.tree.eduservice.controller;

import moe.tree.commontuils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class LoginController {

	@PostMapping("/login")
	public R login() {
		return R.ok().data("token", "admin-token");
	}

	@GetMapping("/info")
	public R info() {
		return R.ok()
				.data("roles", new String[]{"admin"})
				.data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
				.data("name", "Natuki");
	}

	@PostMapping("/logout")
	public R logout() {
		return R.ok();
	}
}