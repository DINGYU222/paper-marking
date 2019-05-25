package com.papermaking.controller;

import com.papermaking.Service.CourseService;
import com.papermaking.Service.KnowledgePointService;
import com.papermaking.Service.PaperService;
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

@RequestMapping("/moMP")
@Controller
public class ManualOperationMakingPaperController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private KnowledgePointService knowledgePointService;
//
//    @Autowired
//    private QuestionBankService questionService;
//
    @Autowired
    private PaperService paperService;

    @RequestMapping(method = RequestMethod.GET)
    public String manualOperationMakingPaperFirstStep(Model model) {
        model.addAttribute("courses", courseService.selectAll());
        return "mo/moMPFirstStep";
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

        return "mo/moMPSecondStep";
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
        model.addAttribute("points",points);
        for (String kId : kIds) {
            points.add(knowledgePointService.selectByPrimaryKey(Integer.parseInt(kId)));
        }
        List<Question> xuanzelist = new ArrayList<>();
        List<Question> tiankonglist = new ArrayList<>();
        List<Question> panduanlist = new ArrayList<>();
        List<Question> jiandalist = new ArrayList<>();
        for (KnowledgePoint point : points) {
            for (Question question : point.getQuestions()) {
                switch (question.getqType()) {
                    case "选择题":
                        xuanzelist.add(question);
                        break;
                    case "填空题":
                        tiankonglist.add(question);
                        break;
                    case "简答题":
                        jiandalist.add(question);
                        break;
                    case "判断题":
                        panduanlist.add(question);
                        break;
                }
            }
        }


        model.addAttribute("choiceQuestions", xuanzelist);
        model.addAttribute("fillBlandQuestions", tiankonglist);
        model.addAttribute("judgeQuestions", panduanlist);
        model.addAttribute("answerQuestions", jiandalist);

        return "mo/moMPThirdStep";
    }




    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, HttpServletRequest request,
                       @RequestParam(value = "xuanzelist",required = false) String[] xuanzelist,
                       @RequestParam(value = "tiankonglist",required = false) String[] tiankonglist,
                       @RequestParam(value = "jiandalist",required = false) String[] jiandalist,
                       @RequestParam(value = "panduanlist", required = false) String[] panduanlist) {
        String paperName = request.getParameter("name");
        String cid = request.getParameter("cid");
        String testTypes = request.getParameter("testTypes");
        String difflevel = request.getParameter("difflevel");

        int choiceSocre = Integer.parseInt(request.getParameter("choiceSocre"));
        int fillBlankSocre = Integer.parseInt(request.getParameter("fillBlankSocre"));
        int answerSocre = Integer.parseInt(request.getParameter("answerSocre"));
        int judgeSocre = Integer.parseInt(request.getParameter("judgeSocre"));
        int i = xuanzelist == null ? 0 : xuanzelist.length;
        int score = (xuanzelist == null ? 0 : xuanzelist.length) * choiceSocre
                + (tiankonglist == null ? 0 : tiankonglist.length) * fillBlankSocre
                + (jiandalist == null ? 0 : jiandalist.length) * answerSocre
                + (panduanlist == null ? 0 : panduanlist.length) * judgeSocre;

        Paper paper = new Paper(paperName,Integer.parseInt(cid),testTypes,score,new Date(),choiceSocre,fillBlankSocre,answerSocre,judgeSocre,Double.valueOf(difflevel));

        //保存试卷
        Integer pid = paperService.insert(paper);

        paperService.createPaper02(pid, xuanzelist, tiankonglist, jiandalist, panduanlist);

        return "redirect:/paper";
    }
}
