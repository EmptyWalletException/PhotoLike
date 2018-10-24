package com.kingguanzhang.toptalk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.repositories.CategoryRepository;

//@CacheConfig(cacheNames = "category")
@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * 分页查询所有;
     * @return
     */
    //@Cacheable(value = "category",key = "getMethodName()+'['+#a0.pageNumber+']'+'['+#a0.pageSize+']'+'['+#a0.sort+']'")
    public Page<Category> findAll(Pageable pageable){
        Page<Category> page;
        try {
            page = categoryRepository.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }

        return  page;
    }

  /*  *//**
     * 通过topicId查询关联的categoryId;分页并排序;
     * @param topicId
     * @param pageable
     * @return
     *//*
    public Page<Category> findByTopicId(long topicId,Pageable pageable){
        Page<Category> page;
        try {
            page = categoryRepository.findByTopicId(topicId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }

        return  page;
    }*/

    /**
     * 通过id查询单个;
     * @param id
     * @return
     */
   // @Cacheable(value = "category",key = "getMethodName()+'['+#a0+']'")
    public Category findById(Long id){
        Optional<Category> temp = categoryRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public Category findOne(Example<Category> example){
        Optional<Category> temp = categoryRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<Category> findAllByExample(Example<Category> example, Pageable pageable){
        Page<Category> page = categoryRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Category> findAllById(List<Long> list){
        List<Category> allById = categoryRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
   // @CacheEvict(value = "category")
    public Category save(Category object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
             object = categoryRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
        return object;
    }
    /**
     * 立即持久化并返回id;
     * @param object
     */
    //@CacheEvict(value = "category" )
    public long saveAndFlush(Category object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            Category temp = categoryRepository.saveAndFlush(object);
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
   // @CacheEvict(value = "category" )
    public void saveAll(List<Category> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            categoryRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
   // @CacheEvict(value = "category" )
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            categoryRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    //@CacheEvict(value = "category" )
    public void deleteAll(List<Category> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            categoryRepository.deleteAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 将指定的分类下的所有topic的分类设置成默认分类,即分类id为1的分类;用于删除分类之前调用此方法;
     * @param oldCategoryId
     */
   // @CacheEvict(value = {"category","topic"})
    public void replaceByDefault(Long oldCategoryId,Long newCategoryId){
        try{
            categoryRepository.replaceCategoryInTopic(oldCategoryId,newCategoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 统计总数;
     * @return
     */
    public Long count(){
        long count = categoryRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Category> example){
        long count = categoryRepository.count(example);
        return count;
    }

}
