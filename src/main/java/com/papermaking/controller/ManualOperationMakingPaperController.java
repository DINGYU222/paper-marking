//package com.papermaking.controller;
//
//import com.papermaking.Service.CourseService;
//import com.papermaking.Service.KnowledgePointService;
//import com.papermaking.Service.QuestionBankService;
//import com.papermaking.Service.TestPaperService;
//import com.papermaking.pojo.QuestionBank;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequestMapping("/moMP")
//@Controller
//public class ManualOperationMakingPaperController {
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
//    public String manualOperationMakingPaperFirstStep(Model model) {
//        model.addAttribute("courses", courseService.findAll());
//        return "mo/moMPFirstStep";
//    }
//
//    @RequestMapping(value = "/secondStep", method = RequestMethod.POST)
//    public String secondStep(String name, String cid, String testTypes, String diffLevel, Model model) {
//        model.addAttribute("name", name);
//        model.addAttribute("course", courseService.findById(cid));
//        model.addAttribute("testTypes", testTypes);
//        model.addAttribute("diffLevel", diffLevel);
//
//        //找到所有该课程下的题目
//        List<QuestionBank> questions = questionService.findAllByCid(cid);
//        List<QuestionBank> choiceQuestions = new ArrayList<>();
//        List<QuestionBank> fillBlandQuestions = new ArrayList<>();
//        List<QuestionBank> judgeQuestions = new ArrayList<>();
//        List<QuestionBank> answerQuestions = new ArrayList<>();
//
//        for (QuestionBank question : questions) {
//            if ("选择题".equals(question.getType()))
//                choiceQuestions.add(question);
//            else if ("填空题".equals(question.getType()))
//                fillBlandQuestions.add(question);
//            else if ("简答题".equals(question.getType()))
//                answerQuestions.add(question);
//            else judgeQuestions.add(question);
//        }
//
//        model.addAttribute("choiceQuestions", choiceQuestions);
//        model.addAttribute("fillBlandQuestions", fillBlandQuestions);
//        model.addAttribute("judgeQuestions", judgeQuestions);
//        model.addAttribute("answerQuestions", answerQuestions);
//
//        return "mo/moMPSecondStep";
//    }
//
//    @Transactional
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Model model, HttpServletRequest request,
//                       @RequestParam(value = "xuanzelist",required = false) String[] xuanzelist,
//                       @RequestParam(value = "tiankonglist",required = false) String[] tiankonglist,
//                       @RequestParam(value = "jiandalist",required = false) String[] jiandalist,
//                       @RequestParam(value = "panduanlist", required = false) String[] panduanlist) {
//        String paperName = request.getParameter("name");
//        String cid = request.getParameter("cid");
//        String testTypes = request.getParameter("testTypes");
//
//        int choiceSocre = Integer.parseInt(request.getParameter("choiceSocre"));
//        int fillBlankSocre = Integer.parseInt(request.getParameter("fillBlankSocre"));
//        int answerSocre = Integer.parseInt(request.getParameter("answerSocre"));
//        int judgeSocre = Integer.parseInt(request.getParameter("judgeSocre"));
//        int i = xuanzelist == null ? 0 : xuanzelist.length;
//        int score = (xuanzelist == null ? 0 : xuanzelist.length) * choiceSocre
//                + (tiankonglist == null ? 0 : tiankonglist.length) * fillBlankSocre
//                + (jiandalist == null ? 0 : jiandalist.length) * answerSocre
//                + (panduanlist == null ? 0 : panduanlist.length) * judgeSocre;
//
//        //保存试卷
//        String pid = paperService.save(paperName, cid, testTypes, score, choiceSocre, fillBlankSocre, answerSocre, judgeSocre);
//
//        paperService.createPaper02(pid, xuanzelist, tiankonglist, jiandalist, panduanlist);
//
//        return "redirect:/paper";
//    }
//}
