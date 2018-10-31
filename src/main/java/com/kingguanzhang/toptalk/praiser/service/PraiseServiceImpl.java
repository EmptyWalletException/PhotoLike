package com.kingguanzhang.toptalk.praiser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.essay.entity.Essay;
import com.kingguanzhang.toptalk.essay.repository.EssayRepository;
import com.kingguanzhang.toptalk.praiser.entity.Praise;
import com.kingguanzhang.toptalk.praiser.repository.PraiseRepository;
import com.kingguanzhang.toptalk.story.entity.Story;
import com.kingguanzhang.toptalk.story.repositories.StoryRepository;
import com.kingguanzhang.toptalk.topic.entity.Topic;
import com.kingguanzhang.toptalk.topic.repository.TopicRepository;

@Service
public class PraiseServiceImpl {
    
    @Autowired
    private PraiseRepository praiseRepository;
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
    public Page<Praise> findAll(Pageable pageable){
        Page<Praise> page;
        try {
            page = praiseRepository.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }

        return  page;
    }

    /**
     * 查询用户点赞过的story,分页并排序;
     * @param userId
     * @param pageable
     * @return
     */
    public Page<Story> findPraiseStory(Long userId, Pageable pageable){
        Page<Story> story;
        try {
            story = storyRepository.findPraiseStory(userId, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }
        return story;

    }

    /**
     * 查询用户点赞的topic,分页并排序;
     * @param userId
     * @param pageable
     * @return
     */
    public Page<Topic> findPraiseTopic(Long userId, Pageable pageable){
        Page<Topic> topic;
        try {
            topic = topicRepository.findPraiseTopic(userId, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }
        return topic;

    }


    /**
     * 查询用户点赞的essay,分页并排序;
     * @param userId
     * @param pageable
     * @return
     */
    public Page<Essay> findPraiseEssay(Long userId, Pageable pageable){
        Page<Essay> topic;
        try {
            topic = essayRepository.findPraiseEssay(userId, pageable);
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
    public Praise findById(Long id){
        Optional<Praise> temp = praiseRepository.findById(id);
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
    public Praise findOne(Example<Praise> example){
        Optional<Praise> temp = praiseRepository.findOne(example);
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
    public Page<Praise> findAllByExample(Example<Praise> example, Pageable pageable){
        Page<Praise> page = praiseRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Praise> findAllById(List<Long> list){
        List<Praise> allById = praiseRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    public void save(Praise object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            praiseRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;注意此方法不要用在批量持久化中,会出现id重复的异常;
     * @param object
     */
    public long saveAndFlush(Praise object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            Praise temp = praiseRepository.saveAndFlush(object);
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
    public void saveAll(List<Praise> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            praiseRepository.saveAll(list);
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
            praiseRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }



    /**
     * 删除点赞的topic;
     *
     */
    public void deletePraiseTopic(Long userId,Long topicId){
        if (null == userId || null == topicId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseTopic(userId,topicId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除点赞的essay;
     *
     */
    public void deletePraiseEssay(Long userId,Long essayId){
        if (null == userId || null == essayId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseEssay(userId,essayId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除点赞的story;
     *
     */
    public void deletePraiseStory(Long userId,Long storyId){
        if (null == userId || null == storyId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseStory(userId,storyId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除点赞的comment;
     *
     */
    public void deletePraiseComment(Long userId,Long commentId){
        if (null == userId || null == commentId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseComment(userId,commentId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除essay下所有点赞记录;
     *
     */
    public void deletePraiseEssayByEssayId(Long essayId){
        if ( null == essayId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseEssayByEssayId(essayId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除story下所有点赞记录;
     *
     */
    public void deletePraiseStoryByStoryId(Long storyId){
        if ( null == storyId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseStoryByStory(storyId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除topic下所有点赞记录;
     *
     */
    public void deletePraiseTopicByTopicId(Long topicId){
        if ( null == topicId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseTopicByTopicId(topicId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除story下所有点赞记录;
     *
     */
    public void deletePraiseCommentByCommentId(Long commentId){
        if ( null == commentId){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deletePraiseCommentByCommentId(commentId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    public void deleteAll(List<Praise> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            praiseRepository.deleteAll(list);
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
        long count = praiseRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Praise> example){
        long count = praiseRepository.count(example);
        return count;
    }
}
