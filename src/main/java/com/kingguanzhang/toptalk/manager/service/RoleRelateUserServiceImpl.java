package com.kingguanzhang.toptalk.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.manager.entity.RoleRelateUser;
import com.kingguanzhang.toptalk.manager.repositories.RoleRelateUserRepository;

@Service
public class RoleRelateUserServiceImpl {

    @Autowired
    RoleRelateUserRepository roleRelateUserRepository;

    /**
     * 持久化并返回id;注意此方法不要用在批量持久化中,会出现id重复的异常;
     *
     * @param object
     */
    public long saveAndFlush(RoleRelateUser object) {
        if (null == object) {
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id = null;
        try {
            RoleRelateUser temp = roleRelateUserRepository.saveAndFlush(object);
            id = temp.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
        return id;
    }
}
