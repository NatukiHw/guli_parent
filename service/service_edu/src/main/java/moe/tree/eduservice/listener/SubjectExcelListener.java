package moe.tree.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import moe.tree.eduservice.entity.Subject;
import moe.tree.eduservice.entity.SubjectExcelRowData;
import moe.tree.eduservice.service.SubjectService;
import moe.tree.servicebase.exception.GuliException;

@Slf4j
public class SubjectExcelListener implements ReadListener<SubjectExcelRowData> {

	SubjectService subjectService;

	public SubjectExcelListener() {}
	public SubjectExcelListener(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@Override
	public void invoke(SubjectExcelRowData rowData, AnalysisContext analysisContext) {
		if(rowData == null) {
			throw new GuliException(20001, "读取分类数据为空");
		}

		Subject parentSubject = getSubject(rowData.getParentCatName(), "0");
		if(parentSubject == null) {
			parentSubject = new Subject();
			parentSubject.setParentId("0");
			parentSubject.setTitle(rowData.getParentCatName());
			parentSubject.setSort(0);
			subjectService.save(parentSubject);
		}

		String parentId = parentSubject.getId();
		Subject childSubject = getSubject(rowData.getChildCatName(), parentId);
		if(childSubject == null) {
			childSubject = new Subject();
			childSubject.setParentId(parentId);
			childSubject.setTitle(rowData.getChildCatName());
			childSubject.setSort(0);
			subjectService.save(childSubject);
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}

	private Subject getSubject(String name, String parentId) {
		QueryWrapper<Subject> wrapper = new QueryWrapper<>();
		wrapper.eq("title", name);
		wrapper.eq("parent_id", parentId);
		Subject subject = subjectService.getOne(wrapper);
		return subject;
	}
}
