package com.papermaking.controller;

import com.papermaking.Service.CourseService;
import com.papermaking.Service.TeacherService;
import com.papermaking.pojo.Course;
import com.papermaking.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Course> courses = courseService.selectAll();
        model.addAttribute("courses", courses);
        model.addAttribute("total", courses.size());
        return "course/all_course";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String showSaveView(Model model) {
        List<Teacher> teacher = teacherService.selectAll();
        model.addAttribute("teachers", teacher);
        return "course/save_course";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Course course) {
        courseService.insert(course);
        return "redirect:/course";
    }

    @RequestMapping(value = "/delete/{cid}", method = RequestMethod.GET)
    public String delete(@PathVariable("cid") String cid) {
        courseService.deleteByPrimaryKey(Integer.parseInt(cid));
        return "redirect:/course";
    }

    @RequestMapping(value = "/update/{cid}", method = RequestMethod.GET)
    public String updateShow(@PathVariable String cid, Model model) {

        Course course = courseService.selectByPrimaryKey(Integer.parseInt(cid));
        List<Teacher> teachers = teacherService.selectAll();
        model.addAttribute("course", course);
        model.addAttribute("teachers", teachers);
        return "/course/update_course";
    }

    @RequestMapping(value = "/update/{cid}", method = RequestMethod.POST)
    public String update(Course course, @PathVariable("cid") String cid) {
        course.setcId(Integer.parseInt(cid));
        courseService.updateByPrimaryKey(course);
        return "redirect:/course";
    }

}
