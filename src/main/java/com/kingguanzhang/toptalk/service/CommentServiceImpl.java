package com.kingguanzhang.toptalk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.entity.Comment;
import com.kingguanzhang.toptalk.repository.CommentRepository;

//@CacheConfig(cacheNames = "comment")
@Service
public class CommentServiceImpl {
    
    @Autowired
    private CommentRepository commentRepository;


    /**
     * 分页查询所有;
     * @return
     */
    public Page<Comment> findAll(Pageable pageable){
        Page<Comment> page;
        try {
            page = commentRepository.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }

        return  page;
    }

    /**
     * 通过topicId查询关联的commentId;分页并排序;
     * @param topicId
     * @param pageable
     * @return
     */
    //@Cacheable(value = "comment",key = "getMethodName()+'['+#a0+']'+'['+#a1.pageNumber+']'+'['+#a1.pageSize+']'+'['+#a1.sort+']'")
    public Page<Comment> findByTopicId(long topicId,Pageable pageable){
        Page<Comment> page;
        try {
            page = commentRepository.findByTopicId(topicId,pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询数据库字段时出现异常");
        }
        return  page;
    }

    /**
     * 通过storyId查询关联的commentId;分页并排序;
     * @param storyId
     * @param pageable
     * @return
     */
    //@Cacheable(value = "comment",key = "getMethodName()+'['+#a0+']'+'['+#a1.pageNumber+']'+'['+#a1.pageSize+']'+'['+#a1.sort+']'")
    public Page<Comment> findByStoryId(long storyId,Pageable pageable){
        Page<Comment> page;
        try {
            page = commentRepository.findByStoryId(storyId,pageable);
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
    //@Cacheable(value = "comment",key = "getMethodName()+'['+#a0+']'")
    public Comment findById(Long id){
        Optional<Comment> temp = commentRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public Comment findOne(Example<Comment> example){
        Optional<Comment> temp = commentRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<Comment> findAllByExample(Example<Comment> example, Pageable pageable){
        Page<Comment> page = commentRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<Comment> findAllById(List<Long> list){
        List<Comment> allById = commentRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    //@CacheEvict(value = "comment" )
    public Comment save(Comment object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            object = commentRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
        return object;
    }

    /**
     * 持久化并返回id;
     * @param object
     */
    //@CacheEvict(value = "comment" )
    public long saveAndFlush(Comment object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            Comment temp = commentRepository.saveAndFlush(object);
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
    //@CacheEvict(value = "comment")
    public void saveAll(List<Comment> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
           commentRepository.saveAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("持久化数据库字段时出现异常");
        }
    }

    /**
     * 通过Id删除单条记录;
     * @param id
     */
    //@CacheEvict(value = "comment")
    public void delete(Long id){
        if (null == id){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            commentRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    //@CacheEvict(value = "comment")
    public void deleteAll(List<Comment> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            commentRepository.deleteAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除父评论下的所有子评论;
     * @param supcommentId
     * @return
     */
    //@CacheEvict(value = "comment")
    public int deleteSubcomment(Long supcommentId){
        int rowsNumber = 0;
        try{
            rowsNumber = commentRepository.deleteSubcomment(supcommentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowsNumber;
    }

    /**
     * 根据topicId删除topic下所有评论
     * @param topicId
     * @return
     */
    //@CacheEvict(value = "comment")
    public int deleteByTopicId(Long topicId){
        int rowsNumber = 0;
        try{
            rowsNumber = commentRepository.deleteByTopicId(topicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowsNumber;
    }

    /**
     * 根据storyId删除topic下所有评论
     * @param storyId
     * @return
     */
    //@CacheEvict(value = "comment")
    public int deleteByStoryId(Long storyId){
        int rowsNumber = 0;
        try{
            rowsNumber = commentRepository.deleteByStoryId(storyId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowsNumber;
    }

    /**
     * 统计总数;
     * @return
     */
    public Long count(){
        long count = commentRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<Comment> example){
        long count = commentRepository.count(example);
        return count;
    }


}
