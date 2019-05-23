package com.papermaking.controller;

import com.papermaking.Service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;


//    @RequestMapping(method = RequestMethod.GET)
//    public String allTestPaper(Model model) {
//        List<TestPaper> all = paperService.findAll();
//        model.addAttribute("testPapers", all);
//        model.addAttribute("total", all.size());
//        return "paper/paper_all";
//    }
//
//    @RequestMapping(value = "/delete/{pid}", method = RequestMethod.GET)
//    public String delete(@PathVariable("pid") String pid) {
//        paperService.delete(pid);
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
//        paperService.createDoc(pid,path);
//        return "redirect:/paper";
//    }

}
