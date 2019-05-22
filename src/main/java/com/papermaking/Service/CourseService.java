package com.papermaking.Service;

import com.papermaking.mapper.CourseMapper;
import com.papermaking.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    public int insert(Course course) {
        courseMapper.insert(course);
        return course.getcId();
    }

    //删除课程 会删除该课程的知识点，题库，试卷
    public void deleteByPrimaryKey(Integer cid) {

        courseMapper.deleteByPrimaryKey(cid);
//        pointService.deleteByCid(cid);
//        questionBankService.deleteByCid(cid);
//        paperService.deleteByCid(cid);

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
