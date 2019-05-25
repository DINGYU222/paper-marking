package com.papermaking.Service;

import com.papermaking.mapper.KnowledgePointMapper;
import com.papermaking.mapper.QuestionMapper;
import com.papermaking.pojo.KnowledgePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KnowledgePointService {
    @Autowired
    private KnowledgePointMapper knowledgePointMapper;

    @Autowired
    private QuestionMapper questionMapper;

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
    public void deleteByCid(Integer cid) {
        List<KnowledgePoint> points = knowledgePointMapper.selectAllByCid(cid);
        for (KnowledgePoint point : points) {
            deleteByPrimaryKey(point.getkId());
        }
    }
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
        //删除该知识点的题目
        questionMapper.deleteByKid(kid);
    }
//
    public List<KnowledgePoint> selectAllByCid(Integer cId) {
        return knowledgePointMapper.selectAllByCid(cId);
    }

}
