package moe.tree.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectExcelRowData {
	@ExcelProperty(value = "一级分类")
	private String parentCatName;

	@ExcelProperty(value = "二级分类")
	private String childCatName;
}
