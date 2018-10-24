package com.kingguanzhang.toptalk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.entity.Essay;
import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.entity.Topic;
import com.kingguanzhang.toptalk.entity.UserFavorite;
import com.kingguanzhang.toptalk.repositories.EssayRepository;
import com.kingguanzhang.toptalk.repositories.StoryRepository;
import com.kingguanzhang.toptalk.repositories.TopicRepository;
import com.kingguanzhang.toptalk.repositories.UserFavoriteRepository;

//@CacheConfig(cacheNames = "userfavorite")
@Service
public class UserFavoriteServiceImpl {
    
    @Autowired
    private UserFavoriteRepository userFavoriteRepository;

    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private EssayRepository essayRepository;

    /**
     * 分页查询所有;
     * @return
     */
    //@Cacheable(value = "userfavorite",key = "getMethodName()+'['+#a0.pageNumber+']'+'['+#a0.pageSize+']'+'['+#a0.sort+']'\"")
    public Page<UserFavorite> findAll(Pageable pageable){
        Page<UserFavorite> page;
        try {
            page = userFavoriteRepository.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }

        return  page;
    }

    /**
     * 查询用户收藏的story,分页并排序;
     * @param userId
     * @param pageable
     * @return
     */
    //@Cacheable(value = "userfavorite",key = "getMethodName()+'['+#a0+']'+'['+#a1.pageNumber+']'+'['+#a1.pageSize+']'+'['+#a1.sort+']'")
    public Page<Story> findFavoriteStory(Long userId,Pageable pageable){
        Page<Story> story;
        try {
            story = storyRepository.findFavoriteStory(userId, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }
        return story;

    }

    /**
     * 查询用户收藏的topic,分页并排序;
     * @param userId
     * @param pageable
     * @return
     */
    //@Cacheable(value = "userfavorite",key = "getMethodName()+'['+#a0+']'+'['+#a1.pageNumber+']'+'['+#a1.pageSize+']'+'['+#a1.sort+']'")
    public Page<Topic> findFavoriteTopic(Long userId,Pageable pageable){
        Page<Topic> topic;
        try {
            topic = topicRepository.findFavoriteTopic(userId, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }
        return topic;

    }


    /**
     * 查询用户收藏的essay,分页并排序;
     * @param userId
     * @param pageable
     * @return
     */
    //@Cacheable(value = "userfavorite",key = "getMethodName()+'['+#a0+']'+'['+#a1.pageNumber+']'+'['+#a1.pageSize+']'+'['+#a1.sort+']'")
    public Page<Essay> findFavoriteEssay(Long userId, Pageable pageable){
        Page<Essay> topic;
        try {
            topic = essayRepository.findFavoriteEssay(userId, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }
        return topic;

    }

    /**
     * 通过id查询单个;
     * @param id
     * @return
     */
    //@Cacheable(value = "userfavorite",key = "getMethodName()+'['+#a0+']'")
    public UserFavorite findById(Long id){
        Optional<UserFavorite> temp = userFavoriteRepository.findById(id);
        if (temp.isPresent()){
            return temp.get();
        }
        return null;
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public UserFavorite findOne(Example<UserFavorite> example){
        Optional<UserFavorite> temp = userFavoriteRepository.findOne(example);
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
    public Page<UserFavorite> findAllByExample(Example<UserFavorite> example, Pageable pageable){
        Page<UserFavorite> page = userFavoriteRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<UserFavorite> findAllById(List<Long> list){
        List<UserFavorite> allById = userFavoriteRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    //@CacheEvict(value = "userfavorite" )
    public void save(UserFavorite object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            userFavoriteRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;注意此方法不要用在批量持久化中,会出现id重复的异常;
     * @param object
     */
    //@CacheEvict(value = "userfavorite" )
    public long saveAndFlush(UserFavorite object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            UserFavorite temp = userFavoriteRepository.saveAndFlush(object);
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
    //@CacheEvict(value = "userfavorite" )
    public void saveAll(List<UserFavorite> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            userFavoriteRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
    //@CacheEvict(value = "userfavorite" )
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userFavoriteRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }



    /**
     * 删除收藏的topic;
     *
     */
    //@CacheEvict(value = "userfavorite" )
    public void deleteFavoriteTopic(Long userId,Long topicId){
        if (null == userId || null == topicId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userFavoriteRepository.deleteFavoriteTopic(userId,topicId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除收藏的essay;
     *
     */
    //@CacheEvict(value = "userfavorite" )
    public void deleteFavoriteEssay(Long userId,Long essayId){
        if (null == userId || null == essayId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userFavoriteRepository.deleteFavoriteEssay(userId,essayId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除收藏的story;
     *
     */
    //@CacheEvict(value = "userfavorite" )
    public void deleteFavoriteStory(Long userId,Long storyId){
        if (null == userId || null == storyId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userFavoriteRepository.deleteFavoriteStory(userId,storyId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    //@CacheEvict(value = "userfavorite" )
    public void deleteAll(List<UserFavorite> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            userFavoriteRepository.deleteAll(list);
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
        long count = userFavoriteRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<UserFavorite> example){
        long count = userFavoriteRepository.count(example);
        return count;
    }
}
