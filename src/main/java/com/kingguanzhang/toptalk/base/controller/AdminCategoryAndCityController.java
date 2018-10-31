package com.kingguanzhang.toptalk.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingguanzhang.toptalk.base.entity.City;
import com.kingguanzhang.toptalk.base.service.CityServiceImpl;
import com.kingguanzhang.toptalk.common.entity.Msg;
import com.kingguanzhang.toptalk.story.controller.Category;
import com.kingguanzhang.toptalk.topic.service.CategoryServiceImpl;



/**
 * 管理员管理topic专辑分类和城市分类;
 */
@Controller
public class AdminCategoryAndCityController {

	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private CityServiceImpl cityService;
	private Logger logger = LoggerFactory.getLogger(AdminCategoryAndCityController.class);

	/**
	 * 跳转到分类和城市管理页面;
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/categoryAndCity", method = RequestMethod.GET)
	public String toAdminCategoryAndCityPage(Model model) {
		Pageable pageable = new PageRequest(0, 999, new Sort(Sort.Direction.DESC, "rank"));
		Page<Category> categorieList = categoryService.findAll(pageable);
		model.addAttribute("categoryList", categorieList.getContent());

		Page<City> cityList = cityService.findAll(pageable);
		model.addAttribute("cityList", cityList.getContent());
		return "admin/adminCategoryAndCity";
	}

	/**
	 * 保存或更新专辑分类信息;
	 * 
	 * @param topicCategoryId
	 * @param topicCategoryName
	 * @param topicCategoryRank
	 * @return
	 */
	@RequestMapping(value = "/admin/topicCategory/edit", method = RequestMethod.POST)
	@ResponseBody
	private Msg editAndSaveTopicCategory(@RequestParam("topicCategoryId") String topicCategoryId,
			@RequestParam("topicCategoryName") String topicCategoryName,
			@RequestParam("topicCategoryRank") String topicCategoryRank) {
		Category category = new Category();
		category.setName(topicCategoryName);
		category.setRank(Integer.valueOf(topicCategoryRank));
		Long topicCategoryIdLong = Long.parseLong(topicCategoryId);
		if (!topicCategoryIdLong.equals(0L)) {
			category.setId(topicCategoryIdLong);
		}
		try {
			categoryService.save(category);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("保存分类信息失败, 分类id: " + topicCategoryId + "异常信息: " + e.getMessage());
			}
			return Msg.fail().setMsg("保存分类信息失败!");
		}
		return Msg.success().setMsg("保存成功!");
	}

	/**
	 * 删除分类;
	 * 
	 * @param topicCategoryId
	 * @return
	 */
	@RequestMapping(value = "/admin/topicCategory/delete", method = RequestMethod.POST)
	@ResponseBody
	private Msg deleteTopicCategory(@RequestParam("topicCategoryId") String topicCategoryId) {
		try {
			// 将此分类下所有的专辑稿件绑定到备份分类中(id为1的分类),然后删除此分类;
			categoryService.replaceByDefault(Long.parseLong(topicCategoryId), Long.parseLong("1"));
			categoryService.delete(Long.parseLong(topicCategoryId));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("删除分类信息失败, 分类id: " + topicCategoryId + "异常信息: " + e.getMessage());
			}
			return Msg.fail().setMsg("删除分类失败!");
		}
		return Msg.success().setMsg("删除成功!");
	}

	/**
	 * 保存或更新城市信息
	 * 
	 * @param cityId
	 * @param cityName
	 * @param cityRank
	 * @return
	 */
	@RequestMapping(value = "/admin/city/edit", method = RequestMethod.POST)
	@ResponseBody
	private Msg editAndSaveCity(@RequestParam("cityId") String cityId, @RequestParam("cityName") String cityName,
			@RequestParam("cityRank") String cityRank) {
		City city = new City();
		city.setName(cityName);
		city.setRank(Integer.valueOf(cityRank));
		Long cityLongId = Long.parseLong(cityId);

		if (!cityLongId.equals(0L)) {
			city.setId(cityLongId);
		}
		try {
			cityService.save(city);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("保存城市信息失败, 分类id: " + cityId + "异常信息: " + e.getMessage());
			}
			return Msg.fail().setMsg("保存城市失败!");
		}
		return Msg.success().setMsg("保存成功!");
	}

	/**
	 * 删除城市
	 * 
	 * @param cityId
	 * @return
	 */
	@RequestMapping(value = "/admin/city/delete", method = RequestMethod.POST)
	@ResponseBody
	private Msg deleteCity(@RequestParam("cityId") String cityId) {
		try {
			// 将此城市下所有的活动和用户绑定到备份城市中(id为1的城市),然后删除此城市
			cityService.replaceCity(Long.parseLong(cityId), 1L);
			cityService.delete(Long.parseLong(cityId));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("删除城市信息失败, 分类id: " + cityId + "异常信息: " + e.getMessage());
			}
			return Msg.fail().setMsg("删除城市失败!");
		}
		return Msg.success().setMsg("删除成功!");
	}

}
