package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.document.TopicDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface TopicElasticSearchRepository extends ElasticsearchRepository<TopicDocument,Long> {


}
