package com.kingguanzhang.toptalk.service;

import com.kingguanzhang.toptalk.entity.Event;
import com.kingguanzhang.toptalk.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "event")
@Service
public class EventServiceImpl {

    @Autowired
    private EventRepository eventRepository;

    /**
     * 自定义的查询方法,通过城市id分页查询所有event;
     * @param cityId
     * @param pageable
     * @return
     */
    public Page<Event> findAllByCityId(Long cityId,Pageable pageable){
        Page<Event> eventPage = eventRepository.findByCategoryId(cityId, pageable);
        return eventPage;
    }


    /**
     * 分页查询所有;
     * @return
     */
    @Cacheable(value = "event",key = "getMethodName()+'['+#a0.pageNumber+']'+'['+#a0.pageSize+']'+'['+#a0.sort+']'")
    public Page<Event> findAll(Pageable pageable){
        Page<Event> page;
        try {
            page = eventRepository.findAll(pageable);
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
    @Cacheable(value = "event",key = "getMethodName()+'['+#a0+']'")
    public Event findById(Long id){
        Optional<Event> temp = eventRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public Event findOne(Example<Event> example){
        Optional<Event> temp = eventRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<Event> findAllByExample(Example<Event> example, Pageable pageable){
        Page<Event> page = eventRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Event> findAllById(List<Long> list){
        List<Event> allById = eventRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    @CacheEvict(value = "event" )
    public void save(Event object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            eventRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;
     * @param object
     */
    @CacheEvict(value = "event" )
    public long saveAndFlush(Event object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            Event temp = eventRepository.saveAndFlush(object);
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
    @CacheEvict(value = "event" )
    public void saveAll(List<Event> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            eventRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
    @CacheEvict(value = "event" )
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            eventRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    @CacheEvict(value = "event" )
    public void deleteAll(List<Event> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            eventRepository.deleteAll(list);
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
        long count = eventRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Event> example){
        long count = eventRepository.count(example);
        return count;
    }
}
