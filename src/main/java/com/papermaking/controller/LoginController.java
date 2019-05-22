//package com.papermaking.controller;
//
//import com.papermaking.Service.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class LoginController {
//    @Autowired
//    private TeacherService teacherService;
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(@RequestParam(value = "msg" , required = false) String msg, Model model) {
//        model.addAttribute("msg", msg);
//        return "login";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String validate(@RequestParam("code") String code, @RequestParam("password") String password) {
//        if (teacherService.validate(code, password)) {
//            return "redirect:/course";
//        }
//        return "redirect:/login?msg=password Error";
//    }
//}
