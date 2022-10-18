package moe.tree.eduservice.service;

import moe.tree.eduservice.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
public interface VideoService extends IService<Video> {

	public boolean removeVideoByCourseId(String courseId);

	public boolean removeVideoById(String videoId);
}
