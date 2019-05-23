package com.papermaking.controller;

import com.papermaking.Service.CourseService;
import com.papermaking.Service.KnowledgePointService;
import com.papermaking.Service.QuestionService;
import com.papermaking.pojo.Course;
import com.papermaking.pojo.KnowledgePoint;
import com.papermaking.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private KnowledgePointService knowledgePointService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Question> questions = questionService.selectAll();
        model.addAttribute("questions", questions);
        model.addAttribute("total", questions.size());
        return "question/question_all";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertView(Model model) {
        model.addAttribute("points", knowledgePointService.selectAll());
        return "question/question_insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(Model model, Question question) {
        questionService.insert(question);
        return "redirect:/question";
    }

    @RequestMapping(value = "/delete/{qid}", method = RequestMethod.GET)
    public String delete(@PathVariable("qid") String qid) {
        questionService.deleteByPrimaryKey(Integer.parseInt(qid));
        return "redirect:/question";
    }

    @RequestMapping(value = "/update/{qid}")
    public String updateView(@PathVariable("qid") String qid, Model model) {
        Question question = questionService.selectByPrimaryKey(Integer.parseInt(qid));
        model.addAttribute("points", knowledgePointService.selectAll());
        model.addAttribute("question", question);
        return "question/update_question";
    }

    @RequestMapping(value = "/update/{qid}", method = RequestMethod.POST)
    public String update(@PathVariable("qid") String qid, Question question) {
        question.setqId(Integer.parseInt(qid));
        question.setqCreatetime(new Date());
        questionService.updateByPrimaryKey(question);
        return "redirect:/question";
    }

    @Autowired
    private CourseService courseService;

    @Transactional
    @RequestMapping(value = "/init")
    public void initPoint() {

        /**
         * 课程
         */
        Course course = new Course();
        course.setcName("军事理论");
        course.settId(1);
        int cid = courseService.insert(course);

        /**
         * 知识点
         */

        KnowledgePoint point = new KnowledgePoint();
        point.setkNumber("1.0");
        point.setkName("背影");
        point.setcId(cid);
        point.setkCreatetime(new Date());
        knowledgePointService.insert(point);

        KnowledgePoint point1 = new KnowledgePoint();
        point1.setkNumber("1.1");
        point1.setkName("匆匆");
        point1.setcId(cid);
        point1.setkCreatetime(new Date());
        knowledgePointService.insert(point1);

        KnowledgePoint point2 = new KnowledgePoint();
        point2.setkNumber("1.2");
        point2.setkName("雷雨");
        point2.setcId(cid);
        point2.setkCreatetime(new Date());
        knowledgePointService.insert(point2);

        KnowledgePoint point3 = new KnowledgePoint();
        point3.setkNumber("1.3");
        point3.setkName("孔雀东南飞");
        point3.setcId(cid);
        point3.setkCreatetime(new Date());
        knowledgePointService.insert(point3);

        KnowledgePoint point4 = new KnowledgePoint();
        point4.setkNumber("1.4");
        point4.setkName("再别康桥");
        point4.setcId(cid);
        point4.setkCreatetime(new Date());
        knowledgePointService.insert(point4);

        KnowledgePoint point5 = new KnowledgePoint();
        point5.setkNumber("1.5");
        point5.setkName("赤壁赋");
        point5.setcId(cid);
        point5.setkCreatetime(new Date());
        knowledgePointService.insert(point5);

        KnowledgePoint point6 = new KnowledgePoint();
        point6.setkNumber("1.6");
        point6.setkName("记念刘和珍君");
        point6.setcId(cid);
        point6.setkCreatetime(new Date());
        knowledgePointService.insert(point6);

        KnowledgePoint point7 = new KnowledgePoint();
        point7.setkNumber("1.7");
        point7.setkName("荷塘月色");
        point7.setcId(cid);
        point7.setkCreatetime(new Date());
        knowledgePointService.insert(point7);

        KnowledgePoint point8 = new KnowledgePoint();
        point8.setkNumber("1.8");
        point8.setkName("古都的秋");
        point8.setcId(cid);
        point8.setkCreatetime(new Date());
        knowledgePointService.insert(point8);

        KnowledgePoint point9 = new KnowledgePoint();
        point9.setkNumber("1.9");
        point9.setkName("离骚");
        point9.setcId(cid);
        point9.setkCreatetime(new Date());
        knowledgePointService.insert(point9);

        /**
         * 题库
         */
        int k = 0;
        for (int i = 1; i <= 40; i++) {
            Question question = new Question();
            question.setqContent("选择题" + i);
            question.setqRightAnswer("正确答案：C");
            question.setkId(i % 10);
            question.setqType("选择题");
            question.setqDifflevel(Math.random());
            questionService.insert(question);
        }

        for (int i = 41; i <= 80; i++) {
            Question question = new Question();
            question.setqContent("填空题" + i);
            question.setqRightAnswer("填空题正确答案:" + i + i + i);
            question.setkId(i % 10);
            question.setqType("填空题");
            question.setqDifflevel(Math.random());
            questionService.insert(question);
        }

        for (int i = 81; i <= 120; i++) {
            Question question = new Question();
            question.setqContent("简答题" + i);
            question.setqRightAnswer("简答题正确答案:" + i + i + i);
            question.setkId(i % 10);
            question.setqType("简答题");
            question.setqDifflevel(Math.random());
            questionService.insert(question);
        }

        for (int i = 121; i <= 161; i++) {
            Question question = new Question();
            question.setqContent("判断题" + i);
            question.setqRightAnswer("判断题正确答案:" + "√");
            question.setkId(i % 10);
            question.setqType("判断题");
            question.setqDifflevel(Math.random());
            questionService.insert(question);
        }
    }

}
