package com.kingguanzhang.toptalk.controller.adminController;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.entity.City;
import com.kingguanzhang.toptalk.service.CategoryServiceImpl;
import com.kingguanzhang.toptalk.service.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员管理topic专辑分类和城市分类;
 */
@Controller
public class AdminCategoryAndCityController {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CityServiceImpl cityService;

    @RequestMapping(value = "/admin/categoryAndCity",method = RequestMethod.GET)
    public String toAdminCategoryAndCityPage(Model model){
        Pageable pageable = new PageRequest(0,999,new Sort(Sort.Direction.DESC,"rank"));
        Page<Category> categorieList = categoryService.findAll(pageable);
        model.addAttribute("categoryList",categorieList.getContent());

        Page<City> cityList = cityService.findAll(pageable);
        model.addAttribute("cityList",cityList.getContent());
        return "admin/adminCategoryAndCity";
    }
}
