package moe.tree.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import moe.tree.eduservice.entity.Teacher;
import moe.tree.eduservice.mapper.TeacherMapper;
import moe.tree.eduservice.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-09-27
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
//	@Autowired
//	private TeacherMapper teacherMapper;
//
//	@Override
//	public boolean removeById(Serializable id) {
//		LambdaUpdateWrapper<Teacher> updateWrapper = new UpdateWrapper<Teacher>().lambda();
//
//		updateWrapper.eq(Teacher::getId, id).set(Teacher::getIsDeleted, 1);
//		int count = teacherMapper.update(new Teacher(), updateWrapper);
//
//		if (count > 0) {
//			return true;
//		}
//		return false;
//	}
}
