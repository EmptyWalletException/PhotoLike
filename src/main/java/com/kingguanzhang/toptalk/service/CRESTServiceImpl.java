package com.kingguanzhang.toptalk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingguanzhang.toptalk.entity.CommentRelateEST;
import com.kingguanzhang.toptalk.repository.CRESTRepository;

@Service
public class CRESTServiceImpl {
    @Autowired
    private CRESTRepository crestRepository;

    /**
     * 分页查询所有;
     * @return
     */
    public Page<CommentRelateEST> findAll(Pageable pageable){
        Page<CommentRelateEST> page;
        try {
            page = crestRepository.findAll(pageable);
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
    public CommentRelateEST findById(Long id){
        Optional<CommentRelateEST> temp = crestRepository.findById(id);
        return temp.get();
    }

    /**
     * 通过Example例子查询单个;
     * @param example
     * @return
     */
    public CommentRelateEST findOne(Example<CommentRelateEST> example){
        Optional<CommentRelateEST> temp = crestRepository.findOne(example);
        return temp.get();
    }

    /**
     * 通过Example查询所有;
     * @param example
     * @param pageable
     * @return
     */
    public Page<CommentRelateEST> findAllByExample(Example<CommentRelateEST> example, Pageable pageable){
        Page<CommentRelateEST> page = crestRepository.findAll(example, pageable);
        return page;
    }

    /**
     * 通过id查询所有;
     * @param list
     * @return
     */
    public List<CommentRelateEST> findAllById(List<Long> list){
        List<CommentRelateEST> allById = crestRepository.findAllById(list);
        return allById;
    }

    /**
     * 持久化单条数据;
     * @param object
     */
    public void save(CommentRelateEST object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            crestRepository.save(object);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("更新数据库字段时出现异常");
        }
    }

    /**
     * 持久化并返回id;
     * @param object
     */
    public long saveAndFlush(CommentRelateEST object){
        if (null == object){
            throw new RuntimeException("传入的参数不能为空");
        }
        Long id=null;
        try {
            CommentRelateEST temp = crestRepository.saveAndFlush(object);
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
    public void saveAll(List<CommentRelateEST> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try {
            crestRepository.saveAll(list);
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
            crestRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除所有;
     * @param list
     */
    public void deleteAll(List<CommentRelateEST> list){
        if (null == list || 0 == list.size()){
            throw new RuntimeException("传入的参数不能为空");
        }
        try{
            crestRepository.deleteAll(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除数据库字段时出现异常");
        }
    }

    /**
     * 删除评论与topic的关系;
     * @param mommentId
     * @param topicId
     * @return
     */
    public int deleteCommentRelateTopic(Long mommentId,Long topicId){
        int rowsNumber =0;
        try{
           rowsNumber = crestRepository.deleteCommentRelateTopic(mommentId, topicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowsNumber;
    }

    /**
     * 删除评论与story的关系;
     * @param mommentId
     * @param storyId
     * @return
     */
    public int deleteCommentRelateStory(Long mommentId,Long storyId){
        int rowsNumber =0;
        try{
            rowsNumber = crestRepository.deleteCommentRelateStory(mommentId, storyId);
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
        long count = crestRepository.count();
        return count;
    }

    /**
     * 通过样子统计;
     * @param example
     * @return
     */
    public Long countByExample(Example<CommentRelateEST> example){
        long count = crestRepository.count(example);
        return count;
    }
}
