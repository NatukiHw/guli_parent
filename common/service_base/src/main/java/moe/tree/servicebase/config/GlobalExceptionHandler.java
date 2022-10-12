package moe.tree.servicebase.config;

import moe.tree.commontuils.R;
import moe.tree.servicebase.exception.GuliException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R anyException(Exception e) {
		e.printStackTrace();
		return R.error().message("服务器内部错误, 已记录日志");
	}

	@ExceptionHandler(GuliException.class)
	@ResponseBody
	public R guliException(GuliException e) {
		e.printStackTrace();
		return R.error().code(e.getCode()).message(e.getMsg());
	}
}
