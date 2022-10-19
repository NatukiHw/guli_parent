package moe.tree.eduservice.client;

import lombok.extern.slf4j.Slf4j;
import moe.tree.commontuils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class VodFileDegradeFeignClient implements VodClient{

	@Override
	public R deleteVideo(String videoId) {
		log.error("VodClient:deleteVideo:EduVod服务状态异常");
		return R.error().message("EduVod服务状态异常");
	}

	@Override
	public R deleteBatchVideos(List<String> videoIdList) {
		log.error("VodClient:deleteBatchVideos:EduVod服务状态异常");
		return R.error().message("EduVod服务状态异常");
	}
}
