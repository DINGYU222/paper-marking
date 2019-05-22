package com.papermaking.Service;

import com.papermaking.mapper.KnowledgePointMapper;
import com.papermaking.pojo.KnowledgePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KnowledgePointService {
    @Autowired
    private KnowledgePointMapper knowledgePointMapper;

//    @Autowired
//    private QuestionBankMapper questionBankMapper;

    public List<KnowledgePoint> selectAll() {
        return knowledgePointMapper.selectAll();
    }

    public KnowledgePoint selectByPrimaryKey(Integer kId) {
        return knowledgePointMapper.selectByPrimaryKey(kId);
    }

    //    public KnowledgePoint findByIdAndCid(String kid,String cid) {
//        return knowledgePointMapper.findByIdAndCid(kid,cid);
//    }
//
//    public void deleteByCid(String cid) {
//        knowledgePointMapper.deleteByCid(cid);
//    }
//
    public void insert(KnowledgePoint point) {
        point.setkCreatetime(new Date());
        knowledgePointMapper.insert(point);
    }
//
    public void updateByPrimaryKey(KnowledgePoint point) {
        point.setkCreatetime(new Date());
        knowledgePointMapper.updateByPrimaryKey(point);
    }
//
    public void deleteByPrimaryKey(Integer kid) {

        //删除知识点
        knowledgePointMapper.deleteByPrimaryKey(kid);
//        //删除该知识点的题库
//        questionBankMapper.deleteByKid(kid);
    }
//
//    public List<KnowledgePoint> findByCid(String cid) {
//        return knowledgePointMapper.findByCid(cid);
//    }
}
