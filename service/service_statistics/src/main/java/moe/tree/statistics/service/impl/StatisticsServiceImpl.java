package moe.tree.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.tree.servicebase.exception.GuliException;
import moe.tree.statistics.client.UcenterClient;
import moe.tree.statistics.entity.Statistics;
import moe.tree.statistics.entity.vo.StatisticsQuery;
import moe.tree.statistics.mapper.StatisticsMapper;
import moe.tree.statistics.service.StatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author natuki
 * @since 2022-11-01
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {

	@Autowired
	UcenterClient ucenterClient;

	@Override
	public void generateDailyStatisticsData(String date) {
		QueryWrapper<Statistics> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("date_calculated", date);
		baseMapper.delete(queryWrapper);

		Integer registerNum = (Integer) ucenterClient.getDailyRegisterCount(date).getData().get("count");
		Integer loginNum = 114; //TODO
		Integer videoViewNum = 514; //TODO
		Integer courseNum = 191; //TODO

		Statistics statistics = new Statistics();
		statistics.setRegisterNum(registerNum);
		statistics.setLoginNum(loginNum);
		statistics.setVideoViewNum(videoViewNum);
		statistics.setCourseNum(courseNum);
		statistics.setDateCalculated(date);

		int res = baseMapper.insert(statistics);
		if(res <= 0) {
			throw new GuliException(20001, "生成统计数据失败");
		}
	}

	@Override
	public Map<String, Object> getStatisticsData(StatisticsQuery query) {
		String begin = query.getBegin();
		String end = query.getEnd();
		Integer type = query.getType();
		QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
		wrapper.between("date_calculated", begin, end);
		wrapper.orderByAsc("date_calculated");
		List<Statistics> list = baseMapper.selectList(wrapper);

		Map<String, Object> resMap = new HashMap<>();
		List<Long> dataList = new ArrayList<>();
		List<String> dateList = new ArrayList<>();
		resMap.put("dataList", dataList);
		resMap.put("dateList", dateList);

		for(Statistics item : list) {
			dateList.add(item.getDateCalculated());
			Integer dataItem = null;
			switch (type) {
				case StatisticsQuery.TYPE_REGISTER_NUM:
					dataItem = item.getRegisterNum();
					break;
				case StatisticsQuery.TYPE_LOGIN_NUM:
					dataItem = item.getLoginNum();
					break;
				case StatisticsQuery.TYPE_VIDEO_VIEW_NUM:
					dataItem = item.getVideoViewNum();
					break;
				case StatisticsQuery.TYPE_COURSE_NUM:
					dataItem = item.getCourseNum();
					break;
			}
			dataList.add(Long.valueOf(dataItem));
		}

		return resMap;
	}
}
