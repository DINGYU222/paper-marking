package com.papermaking.controller;

import com.papermaking.Service.PaperService;
import com.papermaking.pojo.Paper;
import com.papermaking.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @RequestMapping(method = RequestMethod.GET)
    public String allTestPaper(Model model, @RequestParam(value = "message", required = false) String message) {
        List<Paper> papers = paperService.selectAll();
        model.addAttribute("papers", papers);
        model.addAttribute("total", papers.size());
        model.addAttribute("message", message);
        return "paper/paper_all";
    }

    @RequestMapping(value = "/delete/{pid}", method = RequestMethod.GET)
    public String delete(@PathVariable("pid") String pid) {
        paperService.deleteByPrimaryKey(Integer.parseInt(pid));
        return "redirect:/paper";
    }

    @RequestMapping(value = "/details/{pid}")
    public String details(@PathVariable("pid") String pid, Model model) {
        //获得所有的该张试卷的问题
        Paper paper = paperService.selectByPrimaryKey(Integer.parseInt(pid));
        List<Question> questions = paper.getQuestions();
        model.addAttribute("questions", questions);
        model.addAttribute("pid", pid);
        return "/paper/paper_details";
    }

    @RequestMapping(value = "/createDoc/{pid}", method = RequestMethod.POST)
    public String createDoc(@PathVariable("pid") String pid, @RequestParam("path") String path) throws IOException {
        paperService.createDoc(pid, path);
        return "redirect:/paper";
    }

}
