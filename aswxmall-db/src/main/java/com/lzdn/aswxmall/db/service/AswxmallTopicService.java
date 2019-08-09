package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallTopicMapper;
import com.lzdn.aswxmall.db.domain.AswxmallTopic;
import com.lzdn.aswxmall.db.domain.AswxmallTopicExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallTopicService {
    @Resource
    private AswxmallTopicMapper topicMapper;
    private AswxmallTopic.Column[] columns = new AswxmallTopic.Column[]{AswxmallTopic.Column.id, AswxmallTopic.Column.title, AswxmallTopic.Column.subtitle, AswxmallTopic.Column.price, AswxmallTopic.Column.picUrl, AswxmallTopic.Column.readCount};

    public List<AswxmallTopic> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<AswxmallTopic> queryList(int offset, int limit, String sort, String order) {
        AswxmallTopicExample example = new AswxmallTopicExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return topicMapper.selectByExampleSelective(example, columns);
    }

    public int queryTotal() {
        AswxmallTopicExample example = new AswxmallTopicExample();
        example.or().andDeletedEqualTo(false);
        return (int) topicMapper.countByExample(example);
    }

    public AswxmallTopic findById(Integer id) {
        AswxmallTopicExample example = new AswxmallTopicExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return topicMapper.selectOneByExampleWithBLOBs(example);
    }

    public List<AswxmallTopic> queryRelatedList(Integer id, int offset, int limit) {
        AswxmallTopicExample example = new AswxmallTopicExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        List<AswxmallTopic> topics = topicMapper.selectByExample(example);
        if (topics.size() == 0) {
            return queryList(offset, limit, "add_time", "desc");
        }
        AswxmallTopic topic = topics.get(0);

        example = new AswxmallTopicExample();
        example.or().andIdNotEqualTo(topic.getId()).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        List<AswxmallTopic> relateds = topicMapper.selectByExampleWithBLOBs(example);
        if (relateds.size() != 0) {
            return relateds;
        }

        return queryList(offset, limit, "add_time", "desc");
    }

    public List<AswxmallTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        AswxmallTopicExample example = new AswxmallTopicExample();
        AswxmallTopicExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!StringUtils.isEmpty(subtitle)) {
            criteria.andSubtitleLike("%" + subtitle + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return topicMapper.selectByExampleWithBLOBs(example);
    }

    public int updateById(AswxmallTopic topic) {
        topic.setUpdateTime(LocalDateTime.now());
        AswxmallTopicExample example = new AswxmallTopicExample();
        example.or().andIdEqualTo(topic.getId());
        return topicMapper.updateByExampleSelective(topic, example);
    }

    public void deleteById(Integer id) {
        topicMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallTopic topic) {
        topic.setAddTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        topicMapper.insertSelective(topic);
    }


}
