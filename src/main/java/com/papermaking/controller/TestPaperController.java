//package com.papermaking.controller;
//
//import com.papermaking.Service.PaperExtractingService;
//import com.papermaking.Service.QuestionBankService;
//import com.papermaking.Service.TestPaperService;
//import com.papermaking.pojo.QuestionBank;
//import com.papermaking.pojo.TestPaper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//@RequestMapping("/paper")
//public class TestPaperController {
//
//    @Autowired
//    private TestPaperService testPaperService;
//
//    @Autowired
//    private PaperExtractingService extractingService;
//
//    @Autowired
//    private QuestionBankService questionBankService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String allTestPaper(Model model) {
//        List<TestPaper> all = testPaperService.findAll();
//        model.addAttribute("testPapers", all);
//        model.addAttribute("total", all.size());
//        return "paper/paper_all";
//    }
//
//    @RequestMapping(value = "/delete/{pid}", method = RequestMethod.GET)
//    public String delete(@PathVariable("pid") String pid) {
//        testPaperService.delete(pid);
//        return "paper/paper_all";
//    }
//
//    @RequestMapping(value = "/details/{pid}")
//    public String details(@PathVariable("pid") String pid, Model model) {
//        //获得所有的该张试卷的问题
//        List<String> ids = extractingService.findQidByPid(pid);
//        List<QuestionBank> questions = questionBankService.findByIds(ids);
//        model.addAttribute("questions", questions);
//        model.addAttribute("pid", pid);
//        return "/paper/paper_details";
//    }
//
//    @RequestMapping(value = "/createDoc/{pid}", method = RequestMethod.POST)
//    public String createDoc(@PathVariable("pid") String pid,@RequestParam("path") String path) throws IOException {
//        testPaperService.createDoc(pid,path);
//        return "redirect:/paper";
//    }
//
//}
