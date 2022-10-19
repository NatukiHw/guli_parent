package moe.tree.eduservice.client;

import moe.tree.commontuils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

	@DeleteMapping("/eduvod/videos/{videoId}")
	public R deleteVideo(@PathVariable("videoId") String videoId);

	@DeleteMapping("/eduvod/videos/")
	public R deleteBatchVideos(@RequestParam("videoIdList") List<String> videoIdList);
}
