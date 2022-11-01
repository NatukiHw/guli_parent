package moe.tree.statistics.entity.vo;

import lombok.Data;

@Data
public class StatisticsQuery {

	public static final int TYPE_REGISTER_NUM = 1;
	public static final int TYPE_LOGIN_NUM = 2;
	public static final int TYPE_VIDEO_VIEW_NUM = 3;
	public static final int TYPE_COURSE_NUM = 4;

	private String begin;

	private String end;

	private Integer type;
}
