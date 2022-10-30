package moe.tree.educms.service;

import moe.tree.educms.entity.ChapterFront;

import java.util.List;

public interface ChapterFrontService {
	List<ChapterFront> getChapterListByCourseId(String courseId);
}