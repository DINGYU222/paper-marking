package com.papermaking.controller;

import com.papermaking.Service.CourseService;
import com.papermaking.Service.KnowledgePointService;
import com.papermaking.pojo.KnowledgePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/point")
public class KnowledgePointController {

    @Autowired
    private KnowledgePointService knowledgePointService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        List<KnowledgePoint> points = knowledgePointService.selectAll();
        model.addAttribute("points", points);
        model.addAttribute("total", points.size());
        return "KnowledgePoint/all_kl";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveShowView(Model model) {
        model.addAttribute("courses", courseService.selectAll());
        return "KnowledgePoint/save_point";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(KnowledgePoint point) {
        knowledgePointService.insert(point);
        return "redirect:/point";
    }

    //
    @RequestMapping(value = "/update/{kid}", method = RequestMethod.GET)
    public String updateShowView(@PathVariable("kid") String kid, Model model) {
        KnowledgePoint point = knowledgePointService.selectByPrimaryKey(Integer.parseInt(kid));
        model.addAttribute("point", point);
        model.addAttribute("courses", courseService.selectAll());
        return "KnowledgePoint/update_point";
    }

    @RequestMapping(value = "/update/{kid}", method = RequestMethod.POST)
    public String update(@PathVariable("kid") String kid, KnowledgePoint point) {
        point.setkId(Integer.parseInt(kid));
        knowledgePointService.updateByPrimaryKey(point);
        return "redirect:/point";
    }

    @RequestMapping(value = "/delete/{kid}")
    public String delete(@PathVariable("kid") String kid) {
        knowledgePointService.deleteByPrimaryKey(Integer.parseInt(kid));
        return "redirect:/point";
    }
}
