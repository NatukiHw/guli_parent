package moe.tree.statistics.service;

import moe.tree.statistics.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import moe.tree.statistics.entity.vo.StatisticsQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author natuki
 * @since 2022-11-01
 */
public interface StatisticsService extends IService<Statistics> {

	void generateDailyStatisticsData(String date);

	Map<String, Object> getStatisticsData(StatisticsQuery query);
}
