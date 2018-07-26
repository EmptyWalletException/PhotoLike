package com.kingguanzhang.toptalk.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {


    @RequestMapping("/search")
    public String toSearchPage(){
        return "portal/search";
    }

}
