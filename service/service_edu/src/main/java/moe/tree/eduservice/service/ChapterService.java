package moe.tree.eduservice.service;

import moe.tree.eduservice.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import moe.tree.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
public interface ChapterService extends IService<Chapter> {

	List<ChapterVo> getChapterListByCourseId(String courseId);
}
