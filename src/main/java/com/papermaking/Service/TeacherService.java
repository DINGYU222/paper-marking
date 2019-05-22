package com.papermaking.Service;

import com.papermaking.mapper.TeacherMapper;
import com.papermaking.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

//    public boolean validate(String code, String password) {
//        int count = teacherMapper.findByCodeAndPassword(code, password);
//        if (count == 0) return false;
//        else
//            return true;
//    }

    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

}
