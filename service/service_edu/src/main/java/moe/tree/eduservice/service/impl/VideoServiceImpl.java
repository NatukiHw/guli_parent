package moe.tree.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import moe.tree.commontuils.R;
import moe.tree.eduservice.client.VodClient;
import moe.tree.eduservice.entity.Video;
import moe.tree.eduservice.mapper.VideoMapper;
import moe.tree.eduservice.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

	@Autowired
	private VodClient vodClient;

	@Override
	public boolean removeVideoByCourseId(String courseId) {
		QueryWrapper<Video> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);
		wrapper.select("id", "video_source_id");
		List<Video> videoList = baseMapper.selectList(wrapper);

		List<String> videoIdList = new ArrayList<>();
		for(Video item : videoList) {
			String videoSourceId = item.getVideoSourceId();
			if(!StringUtils.isEmpty(videoSourceId)) {
				videoIdList.add(videoSourceId);
			}
		}
		if(videoIdList.size() > 0) {
			R res = vodClient.deleteBatchVideos(videoIdList);
			if(!res.getSuccess()) {
				return false;
			}
		}
		int cnt = baseMapper.delete(wrapper);
		return cnt > 0;
	}

	@Override
	public boolean removeVideoById(String videoId) {
		Video video = baseMapper.selectById(videoId);
		String videoSourceId = video.getVideoSourceId();
		if(!StringUtils.isEmpty(videoSourceId)) {
			R res = vodClient.deleteVideo(videoSourceId);
			if (!res.getSuccess()) {
				return false;
			}
			int result = baseMapper.deleteById(videoId);
			return result > 0;
		}
		return false;
	}
}
