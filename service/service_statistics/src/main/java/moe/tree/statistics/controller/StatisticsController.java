package moe.tree.statistics.controller;

import moe.tree.commontuils.R;
import moe.tree.statistics.entity.vo.StatisticsQuery;
import moe.tree.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-11-01
 */
@RestController
@RequestMapping("/statistics/data")
public class StatisticsController {

	@Autowired
	StatisticsService statisticsService;

	@PostMapping("/{date}")
	public R generateDailyStatisticsData(@PathVariable String date) {
		statisticsService.generateDailyStatisticsData(date);
		return R.ok();
	}

	@PostMapping("/view")
	public R getStatisticsData(@RequestBody StatisticsQuery query) {
		Map<String, Object> map = statisticsService.getStatisticsData(query);
		return R.ok().data(map);
	}
}
