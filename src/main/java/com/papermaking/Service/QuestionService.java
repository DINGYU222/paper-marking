package com.papermaking.Service;

import com.papermaking.mapper.QuestionMapper;
import com.papermaking.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> selectAll() {
        return questionMapper.selectAll();
    }

    public void insert(Question question) {
        question.setqCreatetime(new Date());
        questionMapper.insert(question);
    }

    public Question selectByPrimaryKey(Integer qid) {
        return questionMapper.selectByPrimaryKey(qid);
    }

    public void updateByPrimaryKey(Question question) {
        questionMapper.updateByPrimaryKey(question);
    }

    public void deleteByPrimaryKey(int qid) {
        questionMapper.deleteByPrimaryKey(qid);
    }

//    public void save(QuestionBank questionBank) {
//        questionBank.setQid(snowflakeIdWorker.nextId() + "");
//        questionBank.setCreatTime(new Date());
//        questionMapper.save(questionBank);
//    }
//
//    public List<QuestionBank> findAll() {
//        return questionMapper.findAll();
//    }
//
//    public void delete(String qid) {
//        questionMapper.delete(qid);
//    }
//
//    public int findCountByTypeAndCid(String type, String cid) {
//        return questionMapper.findCountByTypeAndCid(type, cid);
//    }
//
//    public List<QuestionBank> findByIds(List<String> ids) {
//        return questionMapper.findByIds(ids);
//    }
//
//    public void deleteByCid(String cid) {
//        questionMapper.deleteByCid(cid);
//    }
//
//    public QuestionBank findById(String qid) {
//        return questionMapper.findById(qid);
//    }
//
//    public void update(String qid, QuestionBank question) {
//        question.setQid(qid);
//        questionMapper.update(question);
//    }
//
//    public List<QuestionBank> findAllByCid(String cid) {
//        return questionMapper.findAllByCid(cid);
//    }
}
