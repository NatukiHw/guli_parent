package moe.tree.educms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import moe.tree.commontuils.R;
import moe.tree.educms.entity.Banner;
import moe.tree.educms.service.BannerService;
import moe.tree.servicebase.exception.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author natuki
 * @since 2022-10-20
 */
@RestController
@RequestMapping("/educms/banners")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@GetMapping("/")
	public R getBannerList(long page, long limit) {
		Page<Banner> pageBanner = new Page<>(page, limit);
		QueryWrapper<Banner> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("gmt_create");
		bannerService.page(pageBanner, wrapper);
		long total = pageBanner.getTotal();
		List<Banner> records = pageBanner.getRecords();
		return R.ok().data("total", total).data("rows", records);
	}

	@GetMapping("/{id}")
	public R getBanner(@PathVariable String id) {
		Banner banner = bannerService.getById(id);
		return R.ok().data("banner", banner);
	}

	@PostMapping("/")
	public R addBanner(@RequestBody Banner banner) {
		boolean res = bannerService.save(banner);
		if(!res) {
			throw new GuliException(20001, "Banner添加失败");
		}
		return R.ok();
	}

	@PutMapping("/")
	public R updateBanner(@RequestBody Banner banner) {
		boolean res = bannerService.updateById(banner);
		if(!res) {
			throw new GuliException(20001, "Banner更新失败");
		}
		return R.ok();
	}

	@DeleteMapping("/{id}")
	public R deleteBanner(@PathVariable String id) {
		boolean res = bannerService.removeById(id);
		if(!res) {
			throw new GuliException(20001, "Banner删除失败");
		}
		return R.ok();
	}
}
