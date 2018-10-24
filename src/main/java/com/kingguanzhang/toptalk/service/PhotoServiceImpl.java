package com.kingguanzhang.toptalk.service;

import com.kingguanzhang.toptalk.entity.Photo;
import com.kingguanzhang.toptalk.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl {

    @Autowired
    private PhotoRepository photoRepository;


    /**
     * 分页查询所有;
     * @return
     */
    public Page<Photo> findAll(Pageable pageable){
        Page<Photo> page;
        try {
            page = photoRepository.findAll(pageable);
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
    public Photo findById(Long id){
        Optional<Photo> temp = photoRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public Photo findOne(Example<Photo> example){
        Optional<Photo> temp = photoRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<Photo> findAllByExample(Example<Photo> example, Pageable pageable){
        Page<Photo> page = photoRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Photo> findAllById(List<Long> list){
        List<Photo> allById = photoRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    public void save(Photo object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            photoRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;
     * @param object
     */
    public long saveAndFlush(Photo object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            Photo temp = photoRepository.saveAndFlush(object);
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
    public void saveAll(List<Photo> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            photoRepository.saveAll(list);
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
            photoRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    public void deleteAll(List<Photo> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            photoRepository.deleteAll(list);
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
        long count = photoRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Photo> example){
        long count = photoRepository.count(example);
        return count;
    }
}
