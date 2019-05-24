package com.papermaking.controller;

import com.papermaking.Service.CourseService;
import com.papermaking.Service.KnowledgePointService;
import com.papermaking.Service.PaperService;
import com.papermaking.Service.QuestionService;
import com.papermaking.pojo.KnowledgePoint;
import com.papermaking.pojo.Paper;
import com.papermaking.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MP表示组成试卷 Making Paper
 */
@Controller
@RequestMapping("/autoMP")
public class AutoMakingPaperController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private KnowledgePointService knowledgePointService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperService paperService;

    @RequestMapping(method = RequestMethod.GET)
    public String showView(Model model) {
        model.addAttribute("courses", courseService.selectAll());
        return "autoMPFirstStep";
    }

    @RequestMapping(value = "/secondStep", method = RequestMethod.POST)
    public String secondStep(String pName, String cId, String testTypes, String pDifflevel, Model model) {
        model.addAttribute("pName", pName);
        model.addAttribute("course", courseService.selectByPrimaryKey(Integer.parseInt(cId)));
        model.addAttribute("testTypes", testTypes);
        model.addAttribute("pDifflevel", pDifflevel);

        //找到所有该课程下的知识点
        List<KnowledgePoint> points = knowledgePointService.selectAllByCid(Integer.parseInt(cId));
        model.addAttribute("points", points);

        return "autoMPSecondStep";
    }

    @RequestMapping(value = "/third", method = RequestMethod.POST)
    public String thirdStep(String pName, String cId, String testTypes, String pDifflevel,
                            @RequestParam("type") String[] kIds, Model model) {
        model.addAttribute("pName", pName);
        model.addAttribute("course", courseService.selectByPrimaryKey(Integer.parseInt(cId)));
        model.addAttribute("testTypes", testTypes);
        model.addAttribute("pDifflevel", pDifflevel);

        //找到所有该课程下的知识点
        //这里不建议使用循环，我为了图快使用循环，请不要学习，会多次去连接池获取连接
        List<KnowledgePoint> points = new ArrayList<>();
        for (String kId : kIds) {
            points.add(knowledgePointService.selectByPrimaryKey(Integer.parseInt(kId)));
        }
        model.addAttribute("points", points);
        //计算老师选择的知识点下的所有题目数量
        int selectNum = 0, fillBlankNum = 0, answerNum = 0, judgeNum = 0;
        for (KnowledgePoint point : points) {
            for (Question question : point.getQuestions()) {
                switch (question.getqType()) {
                    case "选择题":
                        selectNum++;
                        break;
                    case "填空题":
                        fillBlankNum++;
                        break;
                    case "简答题":
                        answerNum++;
                        break;
                    case "判断题":
                        judgeNum++;
                        break;
                }
            }
        }
        model.addAttribute("selectNum", selectNum);
        model.addAttribute("fillBlankNum", fillBlankNum);
        model.addAttribute("answerNum", answerNum);
        model.addAttribute("judgeNum", judgeNum);

        return "autoMPThirdStep";
    }

    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, HttpServletRequest request, @RequestParam("type") String[] kIds) {
        String paperName = request.getParameter("pName");
        String cid = request.getParameter("cId");
        String testTypes = request.getParameter("testTypes");
        String difflevel = request.getParameter("pDifflevel");
        //被选中的题目个数
        int[] selectQuestionNum = new int[4];
        selectQuestionNum[0] = Integer.parseInt(request.getParameter("choiceCount"));
        selectQuestionNum[1] = Integer.parseInt(request.getParameter("fillBlankCount"));
        selectQuestionNum[2] = Integer.parseInt(request.getParameter("answerCount"));
        selectQuestionNum[3] = Integer.parseInt(request.getParameter("judgeCount"));

        int choiceSocre = Integer.parseInt(request.getParameter("choiceSocre"));
        int fillBlankSocre = Integer.parseInt(request.getParameter("fillBlankSocre"));
        int answerSocre = Integer.parseInt(request.getParameter("answerSocre"));
        int judgeSocre = Integer.parseInt(request.getParameter("judgeSocre"));
        //计算试卷的总分
        int score = selectQuestionNum[0] * choiceSocre + selectQuestionNum[1] * fillBlankSocre + selectQuestionNum[2] * answerSocre + selectQuestionNum[3] * judgeSocre;

        Paper paper = new Paper(paperName, Integer.parseInt(cid), testTypes, score, new Date(), choiceSocre, fillBlankSocre, answerSocre, judgeSocre, Double.valueOf(difflevel));
        //保存试卷
        Integer pid = paperService.insert(paper);
        //开始自动组卷
        boolean b = paperService.createPaper(selectQuestionNum, kIds, pid, cid, Double.valueOf(difflevel));

        if (b == false)
            throw new RuntimeException("生成失败，请调整难度系数,可以修改util包下 AutoGeneratingPaper类的wave字段 调整波动");
        else return "redirect:/paper";

    }
}
