//package com.papermaking.controller;
//
//import com.papermaking.Service.CourseService;
//import com.papermaking.Service.KnowledgePointService;
//import com.papermaking.Service.QuestionBankService;
//import com.papermaking.Service.TestPaperService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * MP表示组成试卷 Making Paper
// */
//@Controller
//@RequestMapping("/autoMP")
//public class AutoMakingPaperController {
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private KnowledgePointService pointService;
//
//    @Autowired
//    private QuestionBankService questionService;
//
//    @Autowired
//    private TestPaperService paperService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String showView(Model model) {
////        String[] types = new String[]{"选择题", "填空题", "简答题", "判断题"};
////        model.addAttribute("types", types);
//
////        model.addAttribute("points", pointService.findAll());
////        model.addAttribute("selectNum", questionService.findCountByType("选择题"));
////        model.addAttribute("fillBlankNum", questionService.findCountByType("填空题"));
////        model.addAttribute("answerNum", questionService.findCountByType("简答题"));
////        model.addAttribute("judgeNum", questionService.findCountByType("判断题"));
//        model.addAttribute("courses", courseService.findAll());
//        return "autoMPFirstStep";
//    }
//
//    @RequestMapping(value = "/secondStep", method = RequestMethod.POST)
//    public String secondStep(String name, String cid, String testTypes, String diffLevel, Model model) {
//        model.addAttribute("name", name);
//        model.addAttribute("course", courseService.findById(cid));
//        model.addAttribute("testTypes", testTypes);
//        model.addAttribute("diffLevel", diffLevel);
//
//        //找到所有该课程下的知识点
//        model.addAttribute("points", pointService.findByCid(cid));
//
//        model.addAttribute("selectNum", questionService.findCountByTypeAndCid(("选择题"), cid));
//        model.addAttribute("fillBlankNum", questionService.findCountByTypeAndCid(("填空题"), cid));
//        model.addAttribute("answerNum", questionService.findCountByTypeAndCid(("简答题"), cid));
//        model.addAttribute("judgeNum", questionService.findCountByTypeAndCid(("判断题"), cid));
//
//        return "autoMPSecondStep";
//    }
//
//    @Transactional
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Model model, HttpServletRequest request, @RequestParam("type") String[] type) {
//        String paperName = request.getParameter("name");
//        String cid = request.getParameter("cid");
//        String testTypes = request.getParameter("testTypes");
//        int[] Y = new int[4];
//        Y[0] = Integer.parseInt(request.getParameter("choiceCount"));
//        Y[1] = Integer.parseInt(request.getParameter("fillBlankCount"));
//        Y[2] = Integer.parseInt(request.getParameter("answerCount"));
//        Y[3] = Integer.parseInt(request.getParameter("judgeCount"));
//
//        int choiceSocre = Integer.parseInt(request.getParameter("choiceSocre"));
//        int fillBlankSocre = Integer.parseInt(request.getParameter("fillBlankSocre"));
//        int answerSocre = Integer.parseInt(request.getParameter("answerSocre"));
//        int judgeSocre = Integer.parseInt(request.getParameter("judgeSocre"));
//        int score = Y[0] * choiceSocre + Y[1] * fillBlankSocre + Y[2] * answerSocre + Y[3] * judgeSocre;
//
//        //保存试卷
//        String pid = paperService.save(paperName, cid, testTypes, score, choiceSocre, fillBlankSocre, answerSocre, judgeSocre);
//
//        paperService.createPaper(Y, type, pid, cid);
//
//        return "redirect:/paper";
//    }
//}
