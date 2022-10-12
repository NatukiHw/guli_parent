package moe.tree.eduservice.service;

import moe.tree.eduservice.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author natuki
 * @since 2022-10-08
 */
public interface SubjectService extends IService<Subject> {

	public void processExcel(MultipartFile file);
}
