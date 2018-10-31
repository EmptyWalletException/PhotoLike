package com.kingguanzhang.toptalk.massager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingguanzhang.toptalk.massager.entity.Message;
import com.kingguanzhang.toptalk.massager.service.MessageServiceImpl;

@Controller
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping("/message")
    public String toMessagePage(Model model){
        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.DESC,"id"));
        Page<Message> messagePage = messageService.findAll(pageable);
        model.addAttribute("messagePage",messagePage);
        return "user/message";
    }
}
