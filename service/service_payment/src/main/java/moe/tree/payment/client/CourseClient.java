package moe.tree.payment.client;

import moe.tree.commontuils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-edu")
public interface CourseClient {

	@GetMapping("/eduservice/courses/{courseId}/publisher")
	R getCoursePublishVo(@PathVariable("courseId") String courseId);
}
