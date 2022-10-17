package moe.tree.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.tree.eduservice.entity.Video;
import moe.tree.eduservice.mapper.VideoMapper;
import moe.tree.eduservice.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

	@Override
	public boolean removeVideoByCourseId(String courseId) {
		QueryWrapper<Video> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);
		int cnt = baseMapper.delete(wrapper);
		return cnt > 0;
	}
}
