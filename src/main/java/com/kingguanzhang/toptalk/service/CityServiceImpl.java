package com.kingguanzhang.toptalk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.entity.City;
import com.kingguanzhang.toptalk.repository.CityRepository;

//@CacheConfig(cacheNames = "city")
@Service
public class CityServiceImpl {

    @Autowired
    private CityRepository cityRepository;


    /**
     * 分页查询所有;
     * @return
     */
   // @Cacheable(value = "city",key = "getMethodName()+'['+#a0.pageNumber+']'+'['+#a0.pageSize+']'+'['+#a0.sort+']'")
    public Page<City> findAll(Pageable pageable){
        Page<City> page;
        try {
            page = cityRepository.findAll(pageable);
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
   // @Cacheable(value = "city",key = "getMethodName()+'['+#a0+']'")
    public City findById(Long id){
        Optional<City> temp = cityRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public City findOne(Example<City> example){
        Optional<City> temp = cityRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<City> findAllByExample(Example<City> example, Pageable pageable){
        Page<City> page = cityRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<City> findAllById(List<Long> list){
        List<City> allById = cityRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
   // @CacheEvict(value = "city" )
    public void save(City object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            cityRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;
     * @param object
     */
   // @CacheEvict(value = "city" )
    public long saveAndFlush(City object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            City temp = cityRepository.saveAndFlush(object);
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
   // @CacheEvict(value = "city")
    public void saveAll(List<City> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            cityRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
   // @CacheEvict(value = "city" )
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            cityRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
   // @CacheEvict(value = "city" )
    public void deleteAll(List<City> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            cityRepository.deleteAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 将指定的城市下的所有活动和用户的所属城市设置成默认城市,即id为1的城市;用于删除城市之前调用此方法;
     * @param oldCityId
     * @param newCityId
     */
   // @CacheEvict(value = {"city","event"})
    public void replaceCity(Long oldCityId,Long newCityId){
        try{
            cityRepository.replaceCityInAllEvent(oldCityId,newCityId);
            cityRepository.replaceCityInAllUser(oldCityId,newCityId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 统计总数;
     * @return
     */
    public Long count(){
        long count = cityRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<City> example){
        long count = cityRepository.count(example);
        return count;
    }
}
