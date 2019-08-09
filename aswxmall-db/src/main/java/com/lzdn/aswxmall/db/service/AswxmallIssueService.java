package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallIssueMapper;
import com.lzdn.aswxmall.db.domain.AswxmallIssue;
import com.lzdn.aswxmall.db.domain.AswxmallIssueExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallIssueService {
    @Resource
    private AswxmallIssueMapper issueMapper;

    public void deleteById(Integer id) {
        issueMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallIssue issue) {
        issue.setAddTime(LocalDateTime.now());
        issue.setUpdateTime(LocalDateTime.now());
        issueMapper.insertSelective(issue);
    }

    public List<AswxmallIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        AswxmallIssueExample example = new AswxmallIssueExample();
        AswxmallIssueExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(question)) {
            criteria.andQuestionLike("%" + question + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return issueMapper.selectByExample(example);
    }

    public int updateById(AswxmallIssue issue) {
        issue.setUpdateTime(LocalDateTime.now());
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

    public AswxmallIssue findById(Integer id) {
        return issueMapper.selectByPrimaryKey(id);
    }
}
