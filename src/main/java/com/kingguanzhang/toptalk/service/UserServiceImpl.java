package com.kingguanzhang.toptalk.service;

import com.kingguanzhang.toptalk.entity.User;
import com.kingguanzhang.toptalk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    /**
     * 分页查询所有;
     * @return
     */
    public Page<User> findAll(Pageable pageable){
        Page<User> page;
        try {
            page = userRepository.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }

        return  page;
    }

    /**
     * 通过id查询单个;
     * @param id
     * @return
     */
    public User findById(Long id){
        Optional<User> temp = userRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public User findOne(Example<User> example){
        Optional<User> temp = userRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<User> findAllByExample(Example<User> example, Pageable pageable){
        Page<User> page = userRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<User> findAllById(List<Long> list){
        List<User> allById = userRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    public void save(User object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            userRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;注意此方法不要用在批量持久化中,会出现id重复的异常;
     * @param object
     */
    public long saveAndFlush(User object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            User temp = userRepository.saveAndFlush(object);
            id = temp.getId();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
        return id;
    }

    /**
     * 持久化所有;
     * @param list
     */
    public void saveAll(List<User> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            userRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    public void deleteAll(List<User> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userRepository.deleteAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 统计总数;
     * @return
     */
    public Long count(){
        long count = userRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<User> example){
        long count = userRepository.count(example);
        return count;
    }
}
