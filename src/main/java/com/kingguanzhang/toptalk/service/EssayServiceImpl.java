package com.kingguanzhang.toptalk.service;

import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.repositories.EssayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "essay")
@Service
public class EssayServiceImpl {

    @Autowired
    private EssayRepository essayRepository;


    /**
     * 自定义的查询方法,通过关键字可状态分页查询所有essay;
     * @param keyword
     * @param status
     * @param pageable
     * @return
     */
    public Page<Essay> findByKeywordAndStatus(String keyword,Integer status,Pageable pageable){
        Page<Essay> essayPage = essayRepository.findByKeywordAndStatus(keyword,status,pageable);
        return essayPage;
    }

    /**
     * 分页查询所有;
     * @return
     */
    @Cacheable(value = "essay",key = "getMethodName()+'['+#a0.pageNumber+']'+'['+#a0.pageSize+']'+'['+#a0.sort+']'")
    public Page<Essay> findAll(Pageable pageable){
        Page<Essay> page;
        try {
            page = essayRepository.findAll(pageable);
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
    @Cacheable(value = "essay",key = "getMethodName()+'['+#a0+']'")
    public Essay findById(Long id){
        Optional<Essay> temp = essayRepository.findById(id);
        if (temp.isPresent()){
            return temp.get();
        }else {
            return null;
        }
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public Essay findOne(Example<Essay> example){
        Optional<Essay> temp = essayRepository.findOne(example);
        if (temp.isPresent()){
            return temp.get();
        }else {
            return null;
        }
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<Essay> findAllByExample(Example<Essay> example, Pageable pageable){
        Page<Essay> page = essayRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Essay> findAllById(List<Long> list){
        List<Essay> allById = essayRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    @CacheEvict(value = "essay" )
    public void save(Essay object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        object.setCollectNumber(0);
        object.setCreatTime(new Date(System.currentTimeMillis()));
        Long id=null;
        try {
            essayRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;
     * @param object
     */
    @CacheEvict(value = "essay" )
    public long saveAndFlush(Essay object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
           Long id=null;
        try {
            Essay temp = essayRepository.saveAndFlush(object);
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
    @CacheEvict(value = "essay" )
    public void saveAll(List<Essay> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            essayRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
    @CacheEvict(value = "essay" )
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            essayRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    @CacheEvict(value = "essay" )
    public void deleteAll(List<Essay> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            essayRepository.deleteAll(list);
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
        long count = essayRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Essay> example){
        long count = essayRepository.count(example);
        return count;
    }
}
