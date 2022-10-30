package moe.tree.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.tree.educms.entity.ChapterFront;
import moe.tree.educms.entity.VideoFront;
import moe.tree.educms.service.ChapterFrontService;
import moe.tree.eduservice.entity.Chapter;
import moe.tree.eduservice.entity.Video;
import moe.tree.eduservice.entity.vo.ChapterVo;
import moe.tree.eduservice.entity.vo.VideoVo;
import moe.tree.eduservice.service.ChapterService;
import moe.tree.eduservice.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterFrontServiceImpl implements ChapterFrontService {
	@Autowired
	ChapterService chapterService;

	@Autowired
	VideoService videoService;

	@Override
	public List<ChapterFront> getChapterListByCourseId(String courseId) {
		QueryWrapper<Chapter> chapterWrapper = new QueryWrapper<>();
		chapterWrapper.eq("course_id", courseId);
		chapterWrapper.orderByDesc("sort");
		chapterWrapper.orderByAsc("gmt_create");
		List<Chapter> chapterList = chapterService.list(chapterWrapper);

		QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
		videoWrapper.eq("course_id", courseId);
		videoWrapper.orderByDesc("sort");
		videoWrapper.orderByAsc("gmt_create");
		List<Video> videoList = videoService.list(videoWrapper);

		List<ChapterFront> rntList = new ArrayList<>();

		for(Chapter chapter : chapterList) {
			ChapterFront chapterFront = new ChapterFront();
			BeanUtils.copyProperties(chapter, chapterFront);
			List<VideoFront> videoFrontList = new ArrayList<>();
			for(Video video : videoList) {
				if(video.getChapterId().equals(chapter.getId())) {
					VideoFront videoFront = new VideoFront();
					BeanUtils.copyProperties(video, videoFront);
					videoFrontList.add(videoFront);
				}
			}
			chapterFront.setChildren(videoFrontList);
			rntList.add(chapterFront);
		}
		return rntList;
	}
}
