package com.kingguanzhang.toptalk.crudTest;

import com.kingguanzhang.toptalk.document.TopicDocument;
import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.repositories.TopicElasticSearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {
    @Autowired
    TopicElasticSearchRepository topicElasticSearchRepository;


    @Test
    public void test() {

        TopicDocument topic = new TopicDocument();
        topic.setId(111);
        topic.setTitle("测试es搜索标题");
        topic.setContent("测试es搜索正文");
        Category category = new Category();
        category.setId(111);
        category.setRank(111);
        category.setName("测试分类");
        topic.setCategory(category);
        topic.setCommentNumber(512);
        topicElasticSearchRepository.index(topic);
    }
}
