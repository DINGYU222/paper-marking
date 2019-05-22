package com.papermaking.controller;

import com.papermaking.Service.QuestionService;
import com.papermaking.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;



    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Question> questions = questionService.selectAll();
        model.addAttribute("questions", questions);
        model.addAttribute("total", questions.size());
        return "question/question_all";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.GET)
//    public String saveView(Model model) {
//        model.addAttribute("courses", courseService.findAll());
//        model.addAttribute("points", pointService.findAll());
//        return "question/question_save";
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Model model, QuestionBank questionBank) {
//        service.save(questionBank);
//        return "redirect:/question";
//    }
//
//    @RequestMapping(value = "/delete/{qid}", method = RequestMethod.GET)
//    public String delete(@PathVariable("qid") String qid) {
//        service.delete(qid);
//        return "redirect:/question";
//    }
//
//    @RequestMapping(value = "/update/{qid}")
//    public String updateView(@PathVariable("qid") String qid, Model model) {
//        QuestionBank question = service.findById(qid);
//        model.addAttribute("dto", new QuestionDTO(
//                question,
//                courseService.findById(question.getCid()),
//                pointService.findByIdAndCid(question.getKid(), question.getCid()))
//        );
//        return "question/update_question";
//    }
//
//    @RequestMapping(value = "/update/{qid}", method = RequestMethod.POST)
//    public String update(@PathVariable("qid") String qid, QuestionBank question) {
//        service.update(qid, question);
//        return "redirect:/question";
//    }
//
//
//    @RequestMapping(value = "/init")
//    public void initPoint() {
//        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 1);
//
//        /**
//         * 课程
//         */
//        Course course = new Course(null,"军事理论","1");
//        String cid = courseService.save(course);
//
//        /**
//         * 知识点
//         */
//        KnowledgePoint point = new KnowledgePoint();
//        point.setKid("1.0");
//        point.setKname("背影");
//        point.setCid(cid);
//        point.setCreateTime(new Date());
//        pointService.insert(point);
//
//        KnowledgePoint point1= new KnowledgePoint();
//        point1.setKid("1.1");
//        point1.setKname("匆匆");
//        point1.setCid(cid);
//        point1.setCreateTime(new Date());
//        pointService.insert(point1);
//
//        KnowledgePoint point2 = new KnowledgePoint();
//        point2.setKid("1.2");
//        point2.setKname("雷雨");
//        point2.setCid(cid);
//        point2.setCreateTime(new Date());
//        pointService.insert(point2);
//
//        KnowledgePoint point3 = new KnowledgePoint();
//        point3.setKid("1.3");
//        point3.setKname("孔雀东南飞");
//        point3.setCid(cid);
//        point3.setCreateTime(new Date());
//        pointService.insert(point3);
//
//        KnowledgePoint point4 = new KnowledgePoint();
//        point4.setKid("1.4");
//        point4.setKname("再别康桥");
//        point4.setCid(cid);
//        point4.setCreateTime(new Date());
//        pointService.insert(point4);
//
//        KnowledgePoint point5 = new KnowledgePoint();
//        point5.setKid("1.5");
//        point5.setKname("赤壁赋");
//        point5.setCid(cid);
//        point5.setCreateTime(new Date());
//        pointService.insert(point5);
//
//        KnowledgePoint point6 = new KnowledgePoint();
//        point6.setKid("1.6");
//        point6.setKname("记念刘和珍君");
//        point6.setCid(cid);
//        point6.setCreateTime(new Date());
//        pointService.insert(point6);
//
//        KnowledgePoint point7 = new KnowledgePoint();
//        point7.setKid("1.7");
//        point7.setKname("荷塘月色");
//        point7.setCid(cid);
//        point7.setCreateTime(new Date());
//        pointService.insert(point7);
//
//        KnowledgePoint point8 = new KnowledgePoint();
//        point8.setKid("1.8");
//        point8.setKname("古都的秋");
//        point8.setCid(cid);
//        point8.setCreateTime(new Date());
//        pointService.insert(point8);
//
//        KnowledgePoint point9 = new KnowledgePoint();
//        point9.setKid("1.9");
//        point9.setKname("离骚");
//        point9.setCid(cid);
//        point9.setCreateTime(new Date());
//        pointService.insert(point9);
//
//        /**
//         * 题库
//         */
//        int k = 0;
//        for (int i = 1; i <= 40; i++) {
//            QuestionBank question = new QuestionBank();
//            question.setQid(i + "");
//            question.setCid(cid);
//            question.setContent("选择题" + i);
//            question.setRightAnswers("正确答案：C");
//            question.setKid(1 + "." + (k++) % 10);
//            question.setType("选择题");
//            question.setQuestionRating(Math.random());
//            service.save(question);
//        }
//
//        for (int i = 41; i <= 80; i++) {
//            QuestionBank question = new QuestionBank();
//            question.setQid(i + "");
//            question.setCid(cid);
//            question.setContent("填空题" + i);
//            question.setRightAnswers("填空题正确答案:" + i + i + i);
//            question.setKid(1 + "." + (k++) % 10);
//            question.setType("填空题");
//            question.setQuestionRating(Math.random());
//            service.save(question);
//        }
//
//        for (int i = 81; i <= 120; i++) {
//            QuestionBank question = new QuestionBank();
//            question.setQid(i + "");
//            question.setCid(cid);
//            question.setContent("简答题" + i);
//            question.setRightAnswers("简答题正确答案:" + i + i + i);
//            question.setKid(1 + "." + (k++) % 10);
//            question.setType("简答题");
//            question.setQuestionRating(Math.random());
//            service.save(question);
//        }
//
//        for (int i = 121; i <= 161; i++) {
//            QuestionBank question = new QuestionBank();
//            question.setQid(i + "");
//            question.setCid(cid);
//            question.setContent("判断题" + i);
//            question.setRightAnswers("判断题正确答案:" + "√");
//            question.setKid(1 + "." + (k++) % 10);
//            question.setType("判断题");
//            question.setQuestionRating(Math.random());
//            service.save(question);
//        }
//    }

}
