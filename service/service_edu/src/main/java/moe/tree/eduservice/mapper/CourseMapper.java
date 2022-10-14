package moe.tree.eduservice.mapper;

import moe.tree.eduservice.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import moe.tree.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author natuki
 * @since 2022-10-09
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

	public CoursePublishVo getCoursePublishVo(String courseId);
}
