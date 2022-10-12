package moe.tree.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import moe.tree.eduservice.entity.Subject;
import moe.tree.eduservice.entity.SubjectExcelRowData;
import moe.tree.eduservice.listener.SubjectExcelListener;
import moe.tree.eduservice.mapper.SubjectMapper;
import moe.tree.eduservice.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-10-08
 */
@Service
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

	@Override
	public void processExcel(MultipartFile file){
		try {
			InputStream in = file.getInputStream();
			EasyExcel.read(in, SubjectExcelRowData.class, new SubjectExcelListener(this)).sheet().doRead();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
