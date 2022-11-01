package moe.tree.statistics.client;

import moe.tree.commontuils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
	@GetMapping("/ucenter/members/register/statistics/{day}")
	R getDailyRegisterCount(@PathVariable("day") String day);
}
