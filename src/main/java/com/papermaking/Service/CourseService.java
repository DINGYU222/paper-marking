package com.papermaking.Service;

import com.papermaking.mapper.CourseMapper;
import com.papermaking.mapper.KnowledgePointMapper;
import com.papermaking.mapper.PaperMapper;
import com.papermaking.mapper.QuestionMapper;
import com.papermaking.pojo.Course;
import com.papermaking.pojo.KnowledgePoint;
import com.papermaking.pojo.Paper;
import com.papermaking.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private KnowledgePointMapper knowledgePointMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private PaperMapper paperMapper;

    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    public int insert(Course course) {
        courseMapper.insert(course);
        return course.getcId();
    }

    //删除课程 会删除该课程的知识点，题库，试卷
    public void deleteByPrimaryKey(Integer cid) {
        Course course = courseMapper.selectByPrimaryKey(cid);


        List<KnowledgePoint> knowledgePoints = course.getKnowledgePoints();

        //删除知识点和题目
        //这里不建议用for循环
        for (KnowledgePoint knowledgePoint : knowledgePoints) {
            knowledgePointMapper.deleteByPrimaryKey(knowledgePoint.getkId());
            for (Question question : knowledgePoint.getQuestions()) {
                questionMapper.deleteByPrimaryKey(question.getqId());
            }
        }

        //删除课程
        courseMapper.deleteByPrimaryKey(cid);


        List<Paper> papers = paperMapper.selectAllByCid(cid);
        List<Integer> pIds = new ArrayList<>();
        for (Paper paper : papers) {
            pIds.add(paper.getpId());
        }
        //删除试卷
        paperMapper.deleteByCid(cid);

        //删除 试卷 题目 中间表
        paperMapper.deletePaperQuestionByPids(pIds);
    }

    public Course selectByPrimaryKey(Integer cId) {
        return courseMapper.selectByPrimaryKey(cId);
    }

    //
//    public Course findById(String cid) {
//        return courseMapper.findById(cid);
//    }
//
//    public Teacher findTeachByTid(String tid) {
//        return courseMapper.findTeacherByTid(tid);
//    }
//
//    public List<Teacher> findAllTeacher() {
//        return courseMapper.findAllTeacher();
//    }
//
    public void updateByPrimaryKey(Course course) {
        courseMapper.updateByPrimaryKey(course);
    }
//
}
