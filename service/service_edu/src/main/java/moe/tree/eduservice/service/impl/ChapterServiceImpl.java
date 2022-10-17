package moe.tree.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.tree.eduservice.entity.Chapter;
import moe.tree.eduservice.entity.Video;
import moe.tree.eduservice.entity.vo.ChapterVo;
import moe.tree.eduservice.entity.vo.VideoVo;
import moe.tree.eduservice.mapper.ChapterMapper;
import moe.tree.eduservice.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.tree.eduservice.service.VideoService;
import moe.tree.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

	@Autowired
	private VideoService videoService;

	@Override
	public List<ChapterVo> getChapterListByCourseId(String courseId) {
		QueryWrapper<Chapter> chapterWrapper = new QueryWrapper<>();
		chapterWrapper.eq("course_id", courseId);
		List<Chapter> chapterList = baseMapper.selectList(chapterWrapper);

		QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
		videoWrapper.eq("course_id", courseId);
		List<Video> videoList = videoService.list(videoWrapper);

		List<ChapterVo> rntList = new ArrayList<>();

		for(Chapter chapter : chapterList) {
			ChapterVo chapterVo = new ChapterVo();
			BeanUtils.copyProperties(chapter, chapterVo);
			List<VideoVo> videoVoList = new ArrayList<>();
			for(Video video : videoList) {
				if(video.getChapterId().equals(chapter.getId())) {
					VideoVo videoVo = new VideoVo();
					BeanUtils.copyProperties(video, videoVo);
					videoVoList.add(videoVo);
				}
			}
			chapterVo.setChildren(videoVoList);
			rntList.add(chapterVo);
		}
		return rntList;
	}

	@Override
	public boolean deleteChapter(String chapterId) {
		QueryWrapper<Video> wrapper = new QueryWrapper<>();
		wrapper.eq("chapter_id", chapterId);
		long cnt = videoService.count(wrapper);
		if(cnt > 0) {
			throw new GuliException(20001, "该章节下存在小节, 不能删除");
		}
		int result = baseMapper.deleteById(chapterId);
		return result > 0;
	}

	@Override
	public boolean removeChapterByCourseId(String courseId) {
		QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);
		int cnt = baseMapper.delete(wrapper);
		return cnt > 0;
	}
}
