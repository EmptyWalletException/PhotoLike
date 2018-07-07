package com.kingguanzhang.toptalk.service;

import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl {

    @Autowired
    private TopicRepository topicRepository;

    /**
     * 自定义的查询方法,通过分类id分页查询所有topic;
     * @param categoryId
     * @param pageable
     * @return
     */
    public Page<Topic> findAllByCategoryId(Long categoryId,Pageable pageable){
        Page<Topic> topicPage = topicRepository.findByCategoryId(categoryId, pageable);
        return topicPage;
    }

    /**
     * 分页查询所有;
     * @return
     */
    public Page<Topic> findAll(Pageable pageable){
        Page<Topic> page;
        try {
            page = topicRepository.findAll(pageable);
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
    public Topic findById(long id){
        Optional<Topic> temp = null ;
        try{
            temp = topicRepository.findById(id);
            return temp.get();
        }catch (Exception e){ //如果没有找到此id相关的记录,则返回最新的那条记录;
            Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.DESC,"id"));
            Page<Topic> topicPage = findAll(pageable);
            Topic topic = topicPage.getContent().get(0);
            return topic;
        }

    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public Topic findOne(Example<Topic> example){
        Optional<Topic> temp = topicRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<Topic> findAllByExample(Example<Topic> example, Pageable pageable){
        Page<Topic> page = topicRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Topic> findAllById(List<Long> list){
        List<Topic> allById = topicRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    public void save(Topic object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            topicRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;
     * @param object
     */
    public long saveAndFlush(Topic object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            Topic temp = topicRepository.saveAndFlush(object);
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
    public void saveAll(List<Topic> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            topicRepository.saveAll(list);
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
            topicRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    public void deleteAll(List<Topic> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            topicRepository.deleteAll(list);
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
        long count = topicRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Topic> example){
        long count = topicRepository.count(example);
        return count;
    }
}
